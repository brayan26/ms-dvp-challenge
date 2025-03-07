package com.dvp.challenge.infrastructure.errors;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsersError extends BaseError{

   public static UsersError create() {
      return new UsersError();
   }

   public UsersError notFound() {
      this.setCode("USR-00");
      this.setError("User not found");
      return this;
   }

   public UsersError alreadyExists() {
	  this.setCode("USR-01");
      this.setError("User already exists");
      return this;
   }

   public UsersError invalidUser() {
	  this.setCode("USR-02");
      this.setError("Invalid user password");
      return this;
   }

   public UsersError build() {
      return this;
   }
}
