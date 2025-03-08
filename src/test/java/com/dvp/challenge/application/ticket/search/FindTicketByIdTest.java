package com.dvp.challenge.application.ticket.search;

import com.dvp.challenge.domain.Ticket;
import com.dvp.challenge.domain.TicketMother;
import com.dvp.challenge.domain.repositories.ITicketRepository;
import com.dvp.challenge.infrastructure.exceptions.GenericNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class FindTicketByIdTest {
   @Mock
   private ITicketRepository ticketRepository;

   @InjectMocks
   private FindTicketById findTicketByIdUseCase;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this); // Inicializa los mocks
   }

   @Test
   void shouldGetATicketSuccessfullyById() {
      Ticket ticketToFind = TicketMother.random();
      String ticketId = ticketToFind.id();

      when(ticketRepository.findById(ticketId)).thenReturn(ticketToFind);

      Ticket result = findTicketByIdUseCase.run(ticketId);

      assertEquals(ticketToFind, result);
      verify(ticketRepository, times(1)).findById(ticketId);
   }

   @Test
   void shouldThrowExceptionWhenTicketNotFound() {
      String tickeId = "xxxxxx";

      when(ticketRepository.findById(tickeId)).thenThrow(new GenericNotFoundException("Ticket not found", null));

      GenericNotFoundException thrown = assertThrows(GenericNotFoundException.class, () -> findTicketByIdUseCase.run(tickeId));

      assertEquals("Ticket not found", thrown.getMessage());
      verify(ticketRepository, times(1)).findById(tickeId);
   }
}
