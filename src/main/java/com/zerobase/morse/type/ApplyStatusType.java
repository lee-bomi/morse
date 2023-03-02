package com.zerobase.morse.type;

public enum ApplyStatusType {
  APPLYING("신청중"),
  PERMITTED("허락됨"),
  DENIED("거부됨");


  private final String message;

  private ApplyStatusType(String msg){
    this.message = msg;
  }

  public String getMessage() {
    return message;
  }
}
