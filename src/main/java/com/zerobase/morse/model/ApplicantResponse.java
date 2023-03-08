package com.zerobase.morse.model;

import lombok.Getter;

@Getter
public class ApplicantResponse {

	private String msg;
	private boolean result;

	public ApplicantResponse(String msg, boolean result){
		this.msg = msg;
		this.result = result;
	}
}
