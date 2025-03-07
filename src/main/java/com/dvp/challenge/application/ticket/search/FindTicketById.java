package com.dvp.challenge.application.ticket.search;

import com.dvp.challenge.domain.Ticket;
import com.dvp.challenge.domain.repositories.ITicketRepository;
import org.springframework.stereotype.Component;

@Component
public class FindTicketById {
   private final ITicketRepository repository;

   public FindTicketById(ITicketRepository repository) {
      this.repository = repository;
   }

   public Ticket run(String id) {
      return this.repository.findById(id);
   }
}
