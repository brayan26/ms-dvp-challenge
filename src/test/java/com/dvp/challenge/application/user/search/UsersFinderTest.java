package com.dvp.challenge.application.user.search;

import com.dvp.challenge.domain.User;
import com.dvp.challenge.domain.UserMother;
import com.dvp.challenge.domain.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UsersFinderTest {
   @Mock
   private IUserRepository userRepository;

   @InjectMocks
   private UsersFinder usersFinderUseCase;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this); // Inicializa los mocks
   }

   @Test
   void shouldGetAllUserSuccessfully() {
      User user1 = UserMother.random();
      User user2 = UserMother.random();
      List<User> expectedUsers = List.of(user1, user2);

      when(userRepository.find()).thenReturn(expectedUsers);

      List<User> result = usersFinderUseCase.run();

      assertEquals(expectedUsers.size(), result.size());
      assertEquals(expectedUsers, result);
      verify(userRepository, times(1)).find();
   }

   @Test
   void shouldBeEmptyWhenUserNotFound() {
      when(userRepository.find()).thenReturn(List.of());

      List<User> result = usersFinderUseCase.run();

      assertEquals(0, result.size());
      verify(userRepository, times(1)).find();
   }
}
