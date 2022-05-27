package com.animals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Food not found")
public class FoodNotFoundException extends Exception {
  public FoodNotFoundException() {}
}
