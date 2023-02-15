package com.zerobase.morse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessageHeader {

    private boolean result;
    private String resultCode;
    private String message;
    private int status;
}
