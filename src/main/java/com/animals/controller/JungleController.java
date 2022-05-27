package com.animals.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JungleController {

  @RequestMapping("/")
  public String index() {
    return "Welcome to the Jungle!";
  }
}
