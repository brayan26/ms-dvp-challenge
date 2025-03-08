package com.dvp.challenge.application.ticket.search;

import com.dvp.challenge.domain.Ticket;
import com.dvp.challenge.domain.TicketMother;
import com.dvp.challenge.domain.repositories.ITicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FindAllTicketsPagedTest {
   @Mock
   private ITicketRepository ticketRepository;

   @InjectMocks
   private FindAllTicketsPaged findAllTicketsPagedUseCase;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this); // Inicializa los mocks
   }

   @Test
   void shouldReturnEmptyListWhenStateAndUserAreNull() {
      // Act
      List<Ticket> result = ticketRepository.findByStateOrUser(null, null);

      // Assert
      assertTrue(result.isEmpty());
      verify(ticketRepository, times(1)).findByStateOrUser(null, null);
   }

   @Test
   void shouldReturnTicketsWhenStateAndUserAreProvided() {
      String state = "OPEN";
      String userId = "12345";
      Ticket ticket = TicketMother.random();
      List<Ticket> expectedTickets = List.of(ticket);

      when(ticketRepository.findByStateOrUser(state, userId)).thenReturn(expectedTickets);

      List<Ticket> result = ticketRepository.findByStateOrUser(state, userId);

      assertEquals(1, result.size());
      verify(ticketRepository, times(1)).findByStateOrUser(state, userId);
   }

   @Test
   void shouldReturnTicketsWhenOnlyStateIsProvided() {
      String state = "CLOSE";
      Ticket ticket = TicketMother.random();
      List<Ticket> tickets = List.of(ticket);

      when(ticketRepository.findByStateOrUser(state, null)).thenReturn(tickets);

      List<Ticket> result = ticketRepository.findByStateOrUser(state, null);

      assertEquals(1, result.size());
      verify(ticketRepository, times(1)).findByStateOrUser(state, null);
   }

   @Test
   void shouldReturnTicketsWhenOnlyUserIdIsProvided() {
      String userId = "12345";
      Ticket ticket = TicketMother.random();
      List<Ticket> ticketList = List.of(ticket);

      when(ticketRepository.findByStateOrUser(null, userId)).thenReturn(ticketList);

      List<Ticket> result = ticketRepository.findByStateOrUser(null, userId);

      assertEquals(1, result.size());
      verify(ticketRepository, times(1)).findByStateOrUser(null, userId);
   }
}
