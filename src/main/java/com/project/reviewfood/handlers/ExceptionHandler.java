package com.project.reviewfood.handlers;

import com.project.reviewfood.payloads.responses.DataResponse;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.UnsupportedEncodingException;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public DataResponse handleRuntimeException(RuntimeException e){
        e.printStackTrace();
        return new DataResponse("400", e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public DataResponse handleHttpMessageConversionException(HttpMessageConversionException e){
        e.printStackTrace();
        return new DataResponse("400", e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    @ResponseBody
    public DataResponse handleCustomException(CustomException e){
        e.printStackTrace();
        return new DataResponse(e.getStatus(), e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnsupportedEncodingException.class)
    @ResponseBody
    public DataResponse handleUnsupportedEncodingException(UnsupportedEncodingException e){
        e.printStackTrace();
        return new DataResponse("1012", e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public DataResponse handleException(Exception e){
        e.printStackTrace();
        return new DataResponse("500", e.getMessage());
    }
}
