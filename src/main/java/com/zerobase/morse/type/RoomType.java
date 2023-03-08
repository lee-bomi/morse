package com.zerobase.morse.type;

public enum RoomType {
	INQUIRY_ROOM("inquiry"),
	STUDY_ROOM("study");

	private RoomType(String msg){
		this.msg = msg;
	}

	private String msg;

	public String  getMessage(){
		return this.msg;
	}

}
