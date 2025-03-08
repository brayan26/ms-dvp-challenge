package com.dvp.challenge.application.user.update;

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

public class UserUpdaterTest {
   @Mock
   private IUserRepository userRepository;

   @InjectMocks
   private UserUpdater userUpdaterUseCase;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this); // Inicializa los mocks
   }

   @Test
   void shouldUpdateUserSuccessfully() {
      User userToUpdate = UserMother.random();
      String userId = userToUpdate.id();
      User updatedUser = new User(userId, "Alex", "Prieto Updated", null, null);

      when(userRepository.update(userId, userToUpdate)).thenReturn(updatedUser);

      User result = userUpdaterUseCase.run(userId, userToUpdate);

      assertEquals(updatedUser, result);
      verify(userRepository, times(1)).update(userId, userToUpdate);
   }

   @Test
   void shouldThrowExceptionWhenUserNotFound() {
      String userId = "xxxxxx";
      User userToUpdate = UserMother.random();

      when(userRepository.update(userId, userToUpdate)).thenThrow(new GenericNotFoundException("User not found", null));

      GenericNotFoundException thrown = assertThrows(GenericNotFoundException.class, () -> userUpdaterUseCase.run(userId, userToUpdate));

      assertEquals("User not found", thrown.getMessage());
      verify(userRepository, times(1)).update(userId, userToUpdate);
   }

}
