package com.dvp.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DvpChallengeApplication {

   @Bean
   public PasswordEncoder encoder() {
      return new BCryptPasswordEncoder();
   }

   public static void main(String[] args) {
      SpringApplication.run(DvpChallengeApplication.class, args);
   }

}
