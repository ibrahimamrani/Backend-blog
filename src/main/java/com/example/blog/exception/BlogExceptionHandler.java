package com.example.blog.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class BlogExceptionHandler {

    @ExceptionHandler(BlogException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public VndErrors onProvisionException(BlogException e) {
        return buildVndErrors(e);
    }

    private VndErrors buildVndErrors(Exception e) {
        log.error("{}", e.getMessage());
        String logRef = e.getClass().getSimpleName();
        String msg = e.getMessage();
        return new VndErrors(logRef, msg);
    }
}
