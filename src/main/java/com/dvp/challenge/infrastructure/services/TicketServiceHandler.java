package com.dvp.challenge.infrastructure.services;

import com.dvp.challenge.application.ticket.create.TicketCreator;
import com.dvp.challenge.application.ticket.delete.TicketEraser;
import com.dvp.challenge.application.ticket.search.FindAllTicketsPaged;
import com.dvp.challenge.application.ticket.search.FindTicketById;
import com.dvp.challenge.application.ticket.search.FindTicketByStateOrUserId;
import com.dvp.challenge.application.ticket.update.TicketUpdater;
import com.dvp.challenge.domain.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceHandler {
   private final TicketCreator ticketCreatorUseCase;
   private final TicketUpdater ticketUpdaterUseCase;
   private final FindAllTicketsPaged findAllTicketsPagedUseCase;
   private final FindTicketById findTicketByIdUseCase;
   private final TicketEraser ticketEraserUseCase;
   private final FindTicketByStateOrUserId findTicketByStateOrUserIdUseCase;

   public Ticket create(Ticket dto) {
      return this.ticketCreatorUseCase.run(dto);
   }

   public Ticket edit(String id, Ticket ticket) {
      return this.ticketUpdaterUseCase.run(id, ticket);
   }

   public void delete(String id) {
      this.ticketEraserUseCase.run(id);
   }

   public Ticket findById(String id) {
      return this.findTicketByIdUseCase.run(id);
   }

   public List<Ticket> findAll(int page, int size) {
      return this.findAllTicketsPagedUseCase.run(page, size);
   }

   public List<Ticket> findByStatusOrUserId(String status, String userId) {
      return this.findTicketByStateOrUserIdUseCase.run(status, userId);
   }
}
