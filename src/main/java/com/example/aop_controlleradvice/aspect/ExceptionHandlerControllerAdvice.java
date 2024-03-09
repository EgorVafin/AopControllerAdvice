package com.example.aop_controlleradvice.aspect;

import com.example.aop_controlleradvice.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleResourceNotFoundException(final ResourceNotFoundException exception,
                                                                           final HttpServletRequest request) {
        List<String> details = new ArrayList<>();
        String dateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        details.add("Date and time: " + dateTime);
        details.add("Exception message: " + exception.getMessage());
        details.add("Request URI: " + request.getRequestURI());
        details.add("Request type: " + request.getMethod());
        details.add("Scheme: " + request.getScheme());
        details.add("Local name: " + request.getLocalName());
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        error.setStatus(404);
        error.setDetails(details);

        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleException(final Exception exception,
                                                           final HttpServletRequest request) {

        List<String> details = new ArrayList<>();
        String dateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        details.add("Date and time: " + dateTime);
        details.add("Exception message: " + exception.getMessage());
        details.add("Request URI: " + request.getRequestURI());
        details.add("Request type: " + request.getMethod());
        details.add("Scheme: " + request.getScheme());
        details.add("Local name: " + request.getLocalName());
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        error.setStatus(500);
        error.setDetails(details);

        return error;
    }
}
