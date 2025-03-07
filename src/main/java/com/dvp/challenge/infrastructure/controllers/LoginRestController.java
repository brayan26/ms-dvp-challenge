package com.dvp.challenge.infrastructure.controllers;

import com.dvp.challenge.domain.Login;
import com.dvp.challenge.infrastructure.services.UserServiceHandler;
import com.dvp.challenge.infrastructure.utils.BindingResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {
   @Autowired
   private UserServiceHandler userServiceHandler;

   @PostMapping(path = "/doLogin", produces = {"application/json"}, consumes = {"application/json"})
   public ResponseEntity<?> run(@Valid @RequestBody Login user, BindingResult result) {
      if (result.hasErrors()) {
         return ResponseEntity.badRequest().body(BindingResultUtil.create(result));
      }
      return ResponseEntity.ok(userServiceHandler.doLogin(user.username(), user.password()));
   }
}
