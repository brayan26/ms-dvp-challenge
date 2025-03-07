package com.dvp.challenge;

import com.dvp.challenge.infrastructure.utils.AuthDataUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootApplication
public class DvpChallengeApplication implements ApplicationRunner {

   @Bean
   public PasswordEncoder encoder() {
      return new BCryptPasswordEncoder();
   }

   @PersistenceContext
   private EntityManager entityManager;

   @Autowired
   private Environment env;

   public static void main(String[] args) {
      SpringApplication.run(DvpChallengeApplication.class, args);
   }

   @Override
   @Transactional
   public void run(ApplicationArguments args) throws Exception {
      PasswordEncoder encoder = encoder();
      LocalDateTime now = LocalDateTime.now();

      entityManager.createQuery(env.getProperty("app.init-sql"))
		 .setParameter(1, AuthDataUtil.ID)
		 .setParameter(2, AuthDataUtil.USERNAME)
		 .setParameter(3, encoder.encode(AuthDataUtil.PASSWORD))
		 .executeUpdate();
   }
}
