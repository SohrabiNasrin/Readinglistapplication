package com.nasrinsohrabi.readinglist.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Nasrin on 3/12/2017.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

     public UserNotFoundException(String userId){

           super("com.nasrinsohrabi.readinglist User Not Found" + userId);  }

}
