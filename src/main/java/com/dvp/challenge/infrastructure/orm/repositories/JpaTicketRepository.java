package com.dvp.challenge.infrastructure.orm.repositories;

import com.dvp.challenge.infrastructure.orm.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTicketRepository extends JpaRepository<TicketEntity, String> {

}
