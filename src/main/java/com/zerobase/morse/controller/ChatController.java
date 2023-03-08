package com.zerobase.morse.controller;

import com.zerobase.morse.entity.ChatContent;
import com.zerobase.morse.entity.Member;
import com.zerobase.morse.entity.Study;
import com.zerobase.morse.model.ChatContents;
import com.zerobase.morse.model.InquiryRoomResponse;
import com.zerobase.morse.model.Message;
import com.zerobase.morse.security.TokenProvider;
import com.zerobase.morse.service.ApplicantListService;
import com.zerobase.morse.service.ChatService;
import com.zerobase.morse.service.MemberService;
import com.zerobase.morse.service.StudyService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class ChatController {

  private final ChatService chatService;
  private final ApplicantListService applicantListService;
  private final MemberService memberService;

  private final StudyService studyService;
  private final TokenProvider tokenProvider;



  /**
   * 스터디리스트 페이지에서 상담 버튼을 누르면 열리는 상담방 메소드입니다.
   * @param request request에 담긴 jwt로 로그인한 사용자의 정보를 얻습니다.
   * @param studyNo 스터디리스트페이지에서 버튼을 누르면 스터디의 번호가 전달됩니다.
   * @return 스터디 작성자와 신청자가 참석한 getChatContent를 호출합니다.
   */

  @PostMapping("/inquiry")
  @ResponseBody
  public InquiryRoomResponse makeInquiryChat(HttpServletRequest request, @RequestBody  int studyNo){

    //유저 정보 가져오기
    String token = this.tokenProvider.getTokenFromCookies(request, "access_token");
    String email = this.tokenProvider.getUsername(token);

    // Member, Stduy객체 가져오기
    Member member = this.memberService.getMember(email).get();
    Study study = this.studyService.getStudy(studyNo).get();

    //상담방 만들기
    InquiryRoomResponse inquiryRoomResponse = this.chatService.makeInquiryChat(member,study);

    //신청 리스트에 넣기
    if(inquiryRoomResponse.isResult()) {
      this.applicantListService.addApplicant(member, study, inquiryRoomResponse.getRoomId());
    }

    return inquiryRoomResponse;
  }



  /**
   * 호출하면 상담 채팅방 또는 스터디 채팅방의 내역을 보여주는 메소드입니다.
   * @param model 뷰에 채팅내역을 전달하기위해 사용됩니다.
   * @param chatRoomId 채팅방 번호를 전달해 해당 채팅방 대화내역을 가져오기위해 사용됩니다.
   * @return 해당 채팅방의 대화내역이 나온 페이지를 리턴합니다.
   */

  @GetMapping("/chat/{chatRoomId}")
  public String getChatContent(Model model, @PathVariable Integer chatRoomId) {

    ChatContents contents = chatService.getChatContent(chatRoomId);

    model.addAttribute("contents", contents);

    return "chat";
  }

  @PostMapping("/chat")
  public @ResponseBody boolean sendMessage(@RequestBody Message msg, HttpServletRequest request) {

    //유저 정보 가져오기
    String token = this.tokenProvider.getTokenFromCookies(request, "access_token");
    String email = this.tokenProvider.getUsername(token);

    //대화내용 저장
    ChatContent chatContent = this.chatService.saveChatContent(email, msg);

    if (chatContent == null) {
      return false;
    }

    return true;
  }


}
