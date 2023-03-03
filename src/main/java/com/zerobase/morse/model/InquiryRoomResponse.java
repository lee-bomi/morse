package com.zerobase.morse.model;

import lombok.Getter;

@Getter
public class InquiryRoomResponse {

  private int roomId;
  private String resultMessage;
  private boolean result;

  public InquiryRoomResponse(int roomId, String resultMessage, boolean result) {
    this.roomId = roomId;
    this.resultMessage = resultMessage;
    this.result = result;
  }


}
