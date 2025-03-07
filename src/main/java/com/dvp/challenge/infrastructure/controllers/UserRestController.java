package com.dvp.challenge.infrastructure.controllers;

import com.dvp.challenge.domain.User;
import com.dvp.challenge.infrastructure.services.UserServiceHandler;
import com.dvp.challenge.infrastructure.utils.BindingResultUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
   private final UserServiceHandler serviceHandler;

   @PostMapping(value = "/add", produces = {"application/json"})
   public ResponseEntity<?> create(@Valid @RequestBody User dto, BindingResult result) {
      if (result.hasErrors()) {
         return ResponseEntity.badRequest().body(BindingResultUtil.create(result));
      }
      return ResponseEntity.status(HttpStatus.CREATED).body(serviceHandler.createANewUser(dto));
   }

   @PatchMapping(value = "/edit/{id}", produces = {"application/json"})
   public ResponseEntity<?> update(@Valid @RequestBody User dto, @PathVariable String id, BindingResult result) {
      if (result.hasErrors()) {
         return ResponseEntity.badRequest().body(BindingResultUtil.create(result));
      }
      return ResponseEntity.ok(serviceHandler.edit(id, dto));
   }

   @GetMapping(value = "/findById/{id}", produces = {"application/json"})
   public ResponseEntity<?> findById(@PathVariable String id) {
      return ResponseEntity.ok(serviceHandler.findUserById(id));
   }

   @GetMapping(value = "/findAll", produces = {"application/json"})
   public ResponseEntity<?> findAll() {
      return ResponseEntity.ok(serviceHandler.findAllUser());
   }
}
