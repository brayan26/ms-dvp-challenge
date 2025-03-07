package com.dvp.challenge.infrastructure.controllers;

import com.dvp.challenge.domain.Ticket;
import com.dvp.challenge.infrastructure.services.TicketServiceHandler;
import com.dvp.challenge.infrastructure.utils.BindingResultUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketRestController {
   private final TicketServiceHandler ticketServiceHandler;

   @PostMapping(value = "/add", produces = {"application/json"})
   public ResponseEntity<?> create(@Valid @RequestBody Ticket ticket, BindingResult result) {
      if (result.hasErrors()) {
         return ResponseEntity.badRequest().body(BindingResultUtil.create(result));
      }
      return ResponseEntity.status(HttpStatus.CREATED).body(this.ticketServiceHandler.create(ticket));
   }

   @PatchMapping(value = "/edit/{id}", produces = {"application/json"})
   public ResponseEntity<?> edit(@Valid @RequestBody Ticket ticket, @PathVariable String id,  BindingResult result) {
      if (result.hasErrors()) {
         return ResponseEntity.badRequest().body(BindingResultUtil.create(result));
      }
      return ResponseEntity.ok(this.ticketServiceHandler.edit(id, ticket));
   }

   @DeleteMapping(value = "/delete/{id}", produces = {"application/json"})
   public ResponseEntity<?> delete(@PathVariable String id) {
      this.ticketServiceHandler.delete(id);
      return ResponseEntity.ok().build();
   }

   @GetMapping(value = "/findAll/{page}/{size}", produces = {"application/json"})
   public ResponseEntity<?> findAllPaged(@PathVariable int page, @PathVariable int size) {
      return ResponseEntity.ok(this.ticketServiceHandler.findAll(page, size));
   }

   @GetMapping(value = "/findById/{id}", produces = {"application/json"})
   public ResponseEntity<?> findById(@PathVariable String id) {
      return ResponseEntity.ok(this.ticketServiceHandler.findById(id));
   }

   @GetMapping(value = "/findByStatusOrUser", produces = {"application/json"})
   public ResponseEntity<?> findByStatusOrUser(
         @RequestParam(value = "status", required = false) String status,
         @RequestParam(value = "userId", required = false) String userId) {
      return ResponseEntity.ok(this.ticketServiceHandler.findByStatusOrUserId(status, userId));
   }
}
