package com.dvp.challenge.infrastructure.orm.repositories;

import com.dvp.challenge.infrastructure.orm.entities.Status;
import com.dvp.challenge.infrastructure.orm.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaTicketRepository extends JpaRepository<TicketEntity, String> {
   @Query("SELECT t FROM TicketEntity t WHERE t.status=?1")
   List<TicketEntity> findByState(Status status);

   @Query("SELECT t FROM TicketEntity t WHERE t.user.id=?1")
   List<TicketEntity> findByUserId(String userId);

   @Query("SELECT t FROM TicketEntity t WHERE t.status=?1 AND t.user.id=?2")
   List<TicketEntity> findByStateAndUserId(Status status, String userId);

}
