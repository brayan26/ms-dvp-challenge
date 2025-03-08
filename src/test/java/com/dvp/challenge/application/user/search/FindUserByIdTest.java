package com.dvp.challenge.application.user.search;

import com.dvp.challenge.domain.User;
import com.dvp.challenge.domain.UserMother;
import com.dvp.challenge.domain.repositories.IUserRepository;
import com.dvp.challenge.infrastructure.exceptions.GenericNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FindUserByIdTest {
   @Mock
   private IUserRepository userRepository;

   @InjectMocks
   private FindUserById findUserByIdUseCase;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this); // Inicializa los mocks
   }

   @Test
   void shouldGetAUserSuccessfullyById() {
      User userToFind = UserMother.random();
      String userId = userToFind.id();

      when(userRepository.findUserById(userId)).thenReturn(userToFind);

      User result = findUserByIdUseCase.run(userId);

      assertEquals(userToFind, result);
      verify(userRepository, times(1)).findUserById(userId);
   }

   @Test
   void shouldThrowExceptionWhenUserNotFound() {
      String userId = "xxxxxx";

      when(userRepository.findUserById(userId)).thenThrow(new GenericNotFoundException("User not found", null));

      GenericNotFoundException thrown = assertThrows(GenericNotFoundException.class, () -> findUserByIdUseCase.run(userId));

      assertEquals("User not found", thrown.getMessage());
      verify(userRepository, times(1)).findUserById(userId);
   }
}
