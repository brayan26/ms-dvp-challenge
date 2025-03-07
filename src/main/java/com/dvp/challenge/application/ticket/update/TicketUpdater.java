package com.dvp.challenge.application.ticket.update;

import com.dvp.challenge.domain.Ticket;
import com.dvp.challenge.domain.repositories.ITicketRepository;
import org.springframework.stereotype.Component;

@Component
public class TicketUpdater {
   private final ITicketRepository repository;

   public TicketUpdater(ITicketRepository repository) {
      this.repository = repository;
   }

   public Ticket run(String id, Ticket ticket) {
      return this.repository.edit(id, ticket);
   }
}
