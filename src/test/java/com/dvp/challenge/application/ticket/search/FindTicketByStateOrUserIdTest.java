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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FindTicketByStateOrUserIdTest {
   @Mock
   private ITicketRepository ticketRepository;

   @InjectMocks
   private FindTicketByStateOrUserId findTicketByStateOrUserIdUseCase;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this); // Inicializa los mocks
   }

   @Test
   void shouldGetATicketSuccessfullyById() {
      String status = "OPEN";
      String userId = UUID.randomUUID().toString();
      List<Ticket> expectedResult = List.of(TicketMother.random());

      when(ticketRepository.findByStateOrUser(status, userId)).thenReturn(expectedResult);

      List<Ticket> result = findTicketByStateOrUserIdUseCase.run(status, userId);

      assertEquals(expectedResult, result);
      verify(ticketRepository, times(1)).findByStateOrUser(status, userId);
   }
}
