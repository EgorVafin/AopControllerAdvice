package com.example.aop_controlleradvice.aspect;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExceptionResponse {

    private int status;
    private String message;
    private List<String> details;
}
