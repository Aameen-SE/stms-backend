package com.aameen.stms.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Data
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private int status;

    Map<String,String> errors;
}
