package com.dvp.challenge.application.ticket.search;

import com.dvp.challenge.domain.Ticket;
import com.dvp.challenge.domain.repositories.ITicketRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindTicketByStateOrUserId {
   private final ITicketRepository repository;

   public FindTicketByStateOrUserId(ITicketRepository repository) {
      this.repository = repository;
   }

   public List<Ticket> run(String state, String userId) {
      return this.repository.findByStateOrUser(state, userId);
   }
}
