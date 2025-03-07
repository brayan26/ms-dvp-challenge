package com.dvp.challenge.infrastructure.services;

import com.dvp.challenge.application.token.AccessTokenCreator;
import com.dvp.challenge.application.user.create.UserCreator;
import com.dvp.challenge.application.user.search.FindUserById;
import com.dvp.challenge.application.user.search.UsersFinder;
import com.dvp.challenge.application.user.update.UserUpdater;
import com.dvp.challenge.domain.AccessTokenSerializer;
import com.dvp.challenge.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceHandler {
   private final UserCreator userCreatorUseCase;
   private final FindUserById findUserByIdUseCase;
   private final UsersFinder usersFinderUseCase;
   private final UserUpdater userUpdaterUseCase;
   private final AccessTokenCreator accessTokenCreatorUseCase;

   public AccessTokenSerializer doLogin(String username, String password) {
      return this.accessTokenCreatorUseCase.run(username, password);
   }

   public User edit(String id, User dto) {
      return this.userUpdaterUseCase.run(id, dto);
   }

   public User createANewUser(User dto) {
      return this.userCreatorUseCase.run(dto);
   }

   public User findUserById(String id) {
      return this.findUserByIdUseCase.run(id);
   }

   public List<User> findAllUser() {
      return this.usersFinderUseCase.run();
   }
}
