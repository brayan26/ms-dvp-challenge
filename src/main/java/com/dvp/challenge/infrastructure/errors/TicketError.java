package com.dvp.challenge.infrastructure.errors;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TicketError extends BaseError {

   public static TicketError create() {
      return new TicketError();
   }

   public TicketError notFound() {
      this.setCode("TCK-00");
      this.setError("Ticket not found");
      return this;
   }

   public TicketError alreadyExists() {
      this.setCode("TCK-01");
      this.setError("ticket already exists");
      return this;
   }

   public TicketError build() {
      return this;
   }
}
