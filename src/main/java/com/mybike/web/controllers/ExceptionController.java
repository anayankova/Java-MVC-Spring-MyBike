package com.mybike.web.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({Throwable.class})
    public ModelAndView handleException(Throwable exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        Throwable throwable = exception;
        while (throwable.getCause() != null){
            throwable = throwable.getCause();
        }
        modelAndView.addObject("message", throwable.getMessage());
        return modelAndView;
    }
}
