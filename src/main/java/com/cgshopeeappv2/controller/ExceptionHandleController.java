package com.cgshopeeappv2.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ExceptionHandleController {


    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView handleNoHandlerFound() {
        ModelAndView modelAndView = new ModelAndView("error/error-404");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException() {
        ModelAndView modelAndView = new ModelAndView("error/error-500");
        return modelAndView;
    }
}
