package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
  private String exampleField;
    // Define your endpoints and methods here
    // For example:
    @RequestMapping(method = RequestMethod.GET)
     public String exampleEndpoint() {
         return exampleField;
     }
     @PostMapping
      public String postExample(@RequestParam("exampleField") String exampleField) {
          this.exampleField = exampleField;
          return "The exampleField has been set to: " + exampleField;
      }

}
