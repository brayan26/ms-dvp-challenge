package com.dvp.challenge.infrastructure.orm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "auth")
public class AuthEntity implements Serializable {
   @Id
   @GeneratedValue
   private UUID id;
   @Column(unique = true)
   private String username;
   @Column(name = "password")
   private String password;
}
