package com.dvp.challenge.application.user.search;

import com.dvp.challenge.domain.User;
import com.dvp.challenge.domain.repositories.IUserRepository;
import org.springframework.stereotype.Component;

@Component
public class FindUserById {
   private final IUserRepository repository;

   public FindUserById(IUserRepository repository) {
      this.repository = repository;
   }

   public User run(String id) {
      return repository.findUserById(id);
   }
}
