package com.animals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Food already exists.")
public class FoodAlreadyExistsException extends Exception {

  public FoodAlreadyExistsException() {}
}
