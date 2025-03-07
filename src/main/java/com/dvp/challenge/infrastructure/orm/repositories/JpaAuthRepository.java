package com.dvp.challenge.infrastructure.orm.repositories;

import com.dvp.challenge.infrastructure.orm.entities.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface JpaAuthRepository extends JpaRepository<AuthEntity, UUID> {
   @Query("SELECT a FROM AuthEntity a WHERE a.username=?1")
   Optional<AuthEntity> validateUsername(String username);
}
