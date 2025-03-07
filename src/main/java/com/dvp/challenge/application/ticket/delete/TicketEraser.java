package com.dvp.challenge.application.ticket.delete;

import com.dvp.challenge.domain.repositories.ITicketRepository;
import org.springframework.stereotype.Component;

@Component
public class TicketEraser {
   private final ITicketRepository repository;

   public TicketEraser(ITicketRepository repository) {
      this.repository = repository;
   }

   public void run(String id) {
      this.repository.delete(id);
   }
}
