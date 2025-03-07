package com.dvp.challenge.application.token;

import com.dvp.challenge.domain.AccessTokenSerializer;
import com.dvp.challenge.domain.repositories.IUserRepository;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenCreator {
   private final IUserRepository repository;

   public AccessTokenCreator(IUserRepository repository) {
      this.repository = repository;
   }

   public AccessTokenSerializer run(String username, String password) {
      return this.repository.doLogin(username, password);
   }
}
