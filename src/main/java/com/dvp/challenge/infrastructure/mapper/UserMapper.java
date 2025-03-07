package com.dvp.challenge.infrastructure.mapper;

import com.dvp.challenge.domain.User;
import com.dvp.challenge.infrastructure.orm.entities.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserMapper {

   public static User toDomain(UserEntity source) {
      return new User(
            source.getId(),
            source.getName(),
            source.getLastName(),
            source.getCreatedAt(),
            source.getUpdatedAt());
   }

   public static UserEntity toEntity(User user) {
      LocalDateTime now = LocalDateTime.now();
      return new UserEntity(
            UUID.randomUUID().toString(),
            user.name(), user.lastName(),
            now,
            now);
   }
}
