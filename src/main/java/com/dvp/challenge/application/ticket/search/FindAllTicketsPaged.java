package com.dvp.challenge.application.ticket.search;

import com.dvp.challenge.domain.Ticket;
import com.dvp.challenge.domain.repositories.ITicketRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllTicketsPaged {
   private final ITicketRepository repository;

   public FindAllTicketsPaged(ITicketRepository repository) {
      this.repository = repository;
   }

   public List<Ticket> run(int page, int size) {
      return this.repository.find(page, size);
   }
}
