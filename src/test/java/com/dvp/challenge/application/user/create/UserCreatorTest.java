package com.dvp.challenge.application.user.create;

import com.dvp.challenge.domain.User;
import com.dvp.challenge.domain.UserMother;
import com.dvp.challenge.domain.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserCreatorTest {
   @Mock
   private IUserRepository userRepository;

   @InjectMocks
   private UserCreator userCreatorUseCase;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this); // Inicializa los mocks
   }

   @Test
   void shouldCreateUserSuccessfully() {
      User userToCreate = UserMother.random();
      String userId = userToCreate.id();

      User createdUser = new User(userId, userToCreate.name(), userToCreate.lastName(), LocalDateTime.now(), LocalDateTime.now());
      when(userRepository.create(userToCreate)).thenReturn(createdUser);

      User result = userCreatorUseCase.run(userToCreate);

      assertEquals(createdUser, result);
      verify(userRepository, times(1)).create(userToCreate);
   }
}
