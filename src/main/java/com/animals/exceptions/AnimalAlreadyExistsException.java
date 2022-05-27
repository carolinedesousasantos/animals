package com.animals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Animal already exists.")
public class AnimalAlreadyExistsException extends Exception {}
