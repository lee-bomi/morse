package com.zerobase.morse.controller;

import com.zerobase.morse.entity.ChatRoom;
import com.zerobase.morse.entity.Study;
import com.zerobase.morse.model.ApplicantResponse;
import com.zerobase.morse.service.ApplicantListService;
import com.zerobase.morse.service.ChatService;
import com.zerobase.morse.service.StudyService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class ApplicantListController {

	private final ApplicantListService applicantListService;
	private final ChatService chatService;
	private final StudyService studyService;

	@PostMapping("/permit")
	@ResponseBody
	public ApplicantResponse permitApplicant(Integer studyNo, Integer roomId) {

		// chatRoom, study 가져오기
		Optional<ChatRoom> optionalChatRoom = chatService.getChatRoom(roomId);
		Optional<Study> optionalStudy = studyService.getStudy(studyNo);

		if (optionalChatRoom.isEmpty() || optionalStudy.isEmpty()) {
			return new ApplicantResponse("채팅방 또는 스터디가 없습니다.", false);
		}

		ChatRoom chatRoom = optionalChatRoom.get();
		Study study = optionalStudy.get();

		return applicantListService.permit(study, chatRoom);
	}

	@PostMapping("/deny")
	@ResponseBody
	public ApplicantResponse denyApplicant(Integer studyNo, Integer roomId) {

		// Study, ChatRoom 찾기
		Optional<Study> optionalStudy = studyService.getStudy(studyNo);
		Optional<ChatRoom> optionalChatRoom = chatService.getChatRoom(roomId);

		if (optionalStudy.isEmpty() || optionalChatRoom.isEmpty()) {
			return new ApplicantResponse("채팅방 또는 스터디가 없습니다.", false);
		}

		Study study = optionalStudy.get();
		ChatRoom chatRoom = optionalChatRoom.get();

		//신청자 상태 거절로 바꾸기
		return applicantListService.deny(study, chatRoom);
	}
}
