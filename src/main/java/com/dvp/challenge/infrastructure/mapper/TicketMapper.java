package com.dvp.challenge.infrastructure.mapper;

import com.dvp.challenge.domain.Ticket;
import com.dvp.challenge.infrastructure.orm.entities.Status;
import com.dvp.challenge.infrastructure.orm.entities.TicketEntity;
import com.dvp.challenge.infrastructure.orm.entities.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class TicketMapper {

   public static Ticket toDomain(TicketEntity source) {
      String fullName = source.getUser().getName() + source.getUser().getLastName();
      return new Ticket(
            source.getId(),
            source.getDescription(),
            source.getUser().getId(),
            fullName,
            source.getStatus().toString(),
            source.getCreatedAt(),
            source.getUpdatedAt());
   }

   public static TicketEntity toEntity(Ticket source) {
      LocalDateTime now = LocalDateTime.now();
      return new TicketEntity(
            UUID.randomUUID().toString(),
            source.description(),
            Status.valueOf(source.status()),
            now,
            now,
            new UserEntity(source.userId())
      );
   }
}
