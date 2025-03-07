package com.dvp.challenge.infrastructure.orm.repositories;

import com.dvp.challenge.infrastructure.orm.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, String> {
}
