package com.dvp.challenge.application.user.update;

import com.dvp.challenge.domain.User;
import com.dvp.challenge.domain.repositories.IUserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserUpdater {
   private final IUserRepository repository;

   public UserUpdater(IUserRepository repository) {
      this.repository = repository;
   }

   public User run(String id, User user) {
      return repository.update(id, user);
   }
}
