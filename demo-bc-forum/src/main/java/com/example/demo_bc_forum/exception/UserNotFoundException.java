package com.example.demo_bc_forum.exception;

public class UserNotFoundException extends RuntimeException{
  
  public UserNotFoundException(String message){
    super(message);
  }
}
