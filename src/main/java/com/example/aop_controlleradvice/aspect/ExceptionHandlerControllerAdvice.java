package com.example.aop_controlleradvice.aspect;

import com.example.aop_controlleradvice.exception.ResourceNotFoundException;
import com.example.aop_controlleradvice.exception.ResourceProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleResourceNotFoundException(final ResourceNotFoundException exception,
                                                                           final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        error.setStatus(404);
        error.setDetails(List.of(exception.getLocalizedMessage()));

        return error;
    }

    @ExceptionHandler(ResourceProcessingException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleResourceProcessingException(final Exception exception,
                                                           final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        error.setStatus(500);
        error.setDetails(List.of(exception.getLocalizedMessage()));

        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleException(final Exception exception,
                                                           final HttpServletRequest request) {

        String body = exception.getCause().toString();

        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        error.setStatus(500);
        error.setDetails(null);

        return error;
    }
}
