package com.dvp.challenge.infrastructure.impl;

import com.dvp.challenge.domain.Auth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
   @Getter
   private final String id;
   private final String username;
   @JsonIgnore
   private final String password;

   public UserDetailsImpl(String id, String username, String password) {
      this.id = id;
      this.username = username;
      this.password = password;

   }

   public static UserDetailsImpl create(Auth user) {
      return new UserDetailsImpl(user.id().toString(), user.username(), user.password());
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
