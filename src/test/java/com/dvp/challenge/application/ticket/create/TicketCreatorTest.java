package com.dvp.challenge.application.ticket.create;

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

public class TicketCreatorTest {
   @Mock
   private ITicketRepository ticketRepository;

   @InjectMocks
   private TicketCreator ticketCreatorUseCase;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this); // Inicializa los mocks
   }

   @Test
   void shouldCreateATicketSuccessfully() {
      Ticket ticket = TicketMother.random();

      when(ticketRepository.create(ticket)).thenReturn(ticket);

      Ticket result = ticketCreatorUseCase.run(ticket);

      assertEquals(ticket, result);
      verify(ticketRepository, times(1)).create(ticket);
   }
}
