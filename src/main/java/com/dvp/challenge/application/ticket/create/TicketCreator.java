package com.dvp.challenge.application.ticket.create;

import com.dvp.challenge.domain.Ticket;
import com.dvp.challenge.domain.repositories.ITicketRepository;
import org.springframework.stereotype.Component;

@Component
public class TicketCreator {
   private final ITicketRepository repository;

   public TicketCreator(ITicketRepository repository) {
      this.repository = repository;
   }

   public Ticket run(Ticket ticket) {
      return this.repository.create(ticket);
   }
}
