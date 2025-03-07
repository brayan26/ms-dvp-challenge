package com.dvp.challenge.domain.repositories;

import com.dvp.challenge.domain.Ticket;

import java.util.List;

public interface ITicketRepository {
   Ticket create(Ticket ticket);

   Ticket edit(String id, Ticket ticket);

   void delete(String id);

   Ticket findById(String id);

   List<Ticket> find(int page, int size);

   List<Ticket> findByStateOrUser(String state, String userId);
}
