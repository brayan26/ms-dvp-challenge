package com.dvp.challenge.application.ticket.update;

import com.dvp.challenge.domain.Ticket;
import com.dvp.challenge.domain.TicketMother;
import com.dvp.challenge.domain.repositories.ITicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TicketUpdaterTest {
   @Mock
   private ITicketRepository ticketRepository;

   @InjectMocks
   private TicketUpdater ticketUpdaterUseCase;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this); // Inicializa los mocks
   }

   @Test
   void shouldCreateATicketSuccessfully() {
      Ticket ticket = TicketMother.random();
      String ticketId = ticket.id();

      Ticket createdTicket = new Ticket(ticketId, "Description replaced", ticket.userId(), null, "OPEN", null, null );

      when(ticketRepository.edit(ticketId, ticket)).thenReturn(createdTicket);

      Ticket result = ticketUpdaterUseCase.run(ticketId, ticket);

      assertEquals(createdTicket, result);
      verify(ticketRepository, times(1)).edit(ticketId, ticket);
   }
}
