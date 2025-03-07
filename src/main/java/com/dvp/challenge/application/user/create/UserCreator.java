package com.dvp.challenge.application.user.create;

import com.dvp.challenge.domain.User;
import com.dvp.challenge.domain.repositories.IUserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserCreator {
   private final IUserRepository repository;

   public UserCreator(IUserRepository repository) {
      this.repository = repository;
   }

   public User run(User user) {
      return repository.create(user);
   }
}
