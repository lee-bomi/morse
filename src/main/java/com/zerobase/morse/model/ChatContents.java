package com.zerobase.morse.model;

import com.zerobase.morse.entity.ChatContent;
import java.util.List;
import lombok.Getter;

@Getter
public class ChatContents {

  private int roomId;

  private int studyNo;

  private String writer;

  private List<ChatContent> contents;

  public ChatContents(int roomId,int studyNo,String writer, List<ChatContent> contents) {
    this.roomId = roomId;
    this.studyNo = studyNo;
    this.writer=writer;
    this.contents = contents;
  }
}
