package com.dvp.challenge.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class TicketMother {
   public static Ticket random() {
      LocalDateTime now = LocalDateTime.now();
      return new Ticket(
            UUID.randomUUID().toString(),
            "Post de prueba #1",
            UUID.randomUUID().toString(),
            null,
            "OPEN",
            now,
            now
      );
   }
}
