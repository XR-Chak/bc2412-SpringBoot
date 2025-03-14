package com.example.demo_bc_forum.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo_bc_forum.codewave.ApiResp;
import com.example.demo_bc_forum.model.ErrorResult;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = {UserNotFoundException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResult handleUserNotFound(UserNotFoundException e){
    return new ErrorResult(1,e.getMessage());
  }

  @ExceptionHandler(value = {InvalidInputException.class})//,NumberFormatException.class
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResult handleInvalidInput(){
    return new ErrorResult(2,"Invalid input.");
  }

  @ExceptionHandler(value = {RestTemplateException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResult handleRestTemplateError(RestTemplateException e){
    return new ErrorResult(3, e.getMessage());
  }

  @ExceptionHandler(value = {JsonApiException.class,DBconnectError.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Object> handleApiAndDBError(JsonApiException e){
    ApiResp<Object> apiResp =  ApiResp.builder().syscode(e.getSysCode()).data(new ArrayList<>()).build();
    return apiResp;
  }

}
