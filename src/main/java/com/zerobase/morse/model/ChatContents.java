package com.zerobase.morse.model;

import com.zerobase.morse.entity.ChatContent;
import java.util.List;
import lombok.Getter;

@Getter
public class ChatContents {

  private int roomId;

  private String writer;

  private List<ChatContent> contents;

  public ChatContents(int roomId,String writer, List<ChatContent> contents) {
    this.roomId = roomId;
    this.writer=writer;
    this.contents = contents;
  }
}
