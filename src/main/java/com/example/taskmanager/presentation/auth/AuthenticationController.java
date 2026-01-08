package com.example.taskmanager.presentation.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

  @GetMapping("/login")
  String login() {
    return "auth/login";
  }
}
