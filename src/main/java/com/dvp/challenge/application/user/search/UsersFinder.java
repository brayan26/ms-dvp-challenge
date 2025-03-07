package com.dvp.challenge.application.user.search;

import com.dvp.challenge.domain.User;
import com.dvp.challenge.domain.repositories.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersFinder {
   private final IUserRepository repository;

   public UsersFinder(IUserRepository repository) {
      this.repository = repository;
   }

   public List<User> run() {
      return repository.find();
   }
}
