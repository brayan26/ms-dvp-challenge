package com.dvp.challenge.infrastructure.orm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ticket")
public class TicketEntity implements Serializable {
   @Id
   private String id;

   @Column(length = 500)
   private String description;

   @Enumerated(EnumType.STRING)
   private Status status;

   @Column(name = "created_at", updatable = false)
   private LocalDateTime createdAt;

   @Column(name = "updated_at")
   private LocalDateTime updatedAt;

   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
   private UserEntity user;
}
