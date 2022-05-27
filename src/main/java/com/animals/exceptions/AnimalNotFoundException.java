package com.animals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Animal not found.")
public class AnimalNotFoundException extends Exception {
  public AnimalNotFoundException() {}
}
