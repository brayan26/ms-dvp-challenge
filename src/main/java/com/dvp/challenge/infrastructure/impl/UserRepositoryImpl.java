package com.dvp.challenge.infrastructure.impl;

import com.dvp.challenge.domain.AccessTokenSerializer;
import com.dvp.challenge.domain.User;
import com.dvp.challenge.infrastructure.errors.UsersError;
import com.dvp.challenge.domain.repositories.IUserRepository;
import com.dvp.challenge.infrastructure.exceptions.GenericBadRequestException;
import com.dvp.challenge.infrastructure.exceptions.GenericNotFoundException;
import com.dvp.challenge.infrastructure.mapper.UserMapper;
import com.dvp.challenge.infrastructure.orm.entities.AuthEntity;
import com.dvp.challenge.infrastructure.orm.entities.UserEntity;
import com.dvp.challenge.infrastructure.orm.repositories.JpaAuthRepository;
import com.dvp.challenge.infrastructure.orm.repositories.JpaUserRepository;
import com.dvp.challenge.infrastructure.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {
   private final JpaUserRepository jpaUserRepository;
   private final JpaAuthRepository jpaAuthRepository;
   private final PasswordEncoder encoder;
   private final JwtUtils jwtUtils;

   @Override
   @Transactional
   public User create(User user) {
      UserEntity saved = this.jpaUserRepository.save(UserMapper.toEntity(user));
      return UserMapper.toDomain(saved);
   }

   @Override
   @Transactional
   public User update(String id, User user) {
      Optional<UserEntity> entity = jpaUserRepository.findById(id);
      if (entity.isEmpty()) {
         throw new GenericNotFoundException(
               String.format("<UserRepositoryImpl.findUserById> userId %s, not found in database", id),
               UsersError.create().notFound());
      }
      UserEntity toSave = UserMapper.toEntity(user);
      toSave.setId(id);
      toSave.setCreatedAt(entity.get().getCreatedAt());
      return UserMapper.toDomain(this.jpaUserRepository.save(toSave));
   }

   @Override
   @Transactional(readOnly = true)
   public List<User> find() {
      return this.jpaUserRepository.findAll().stream()
            .map(UserMapper::toDomain)
            .toList();
   }

   @Override
   @Transactional(readOnly = true)
   public User findUserById(String id) {
      Optional<UserEntity> entity = this.jpaUserRepository.findById(id);
      if (entity.isEmpty()) {
         throw new GenericNotFoundException(
               String.format("<UserRepositoryImpl.findUserById> userId %s, not found in database", id),
               UsersError.create().notFound());
      }
      return UserMapper.toDomain(entity.get());
   }

   @Override
   @Transactional(readOnly = true)
   public AccessTokenSerializer doLogin(String username, String password) {
      Optional<AuthEntity> entity = this.jpaAuthRepository.validateUsername(username);
      if (entity.isEmpty()) {
         throw new GenericNotFoundException(
               String.format("<UserRepositoryImpl.doLogin> username '%s' not found in database", username),
               UsersError.create().invalidUser().build()
         );
      }
      if (!encoder.matches(password, entity.get().getPassword())) {
         throw new GenericBadRequestException(
               String.format("<UserRepositoryImpl.doLogin> password for the username '%s' doesn't match", username),
               UsersError.create().invalidUser().build()
         );
      }
      return new AccessTokenSerializer(jwtUtils.generateJwtToken(username));
   }
}
