package com.dvp.challenge.infrastructure.impl;

import com.dvp.challenge.domain.Ticket;
import com.dvp.challenge.infrastructure.errors.TicketError;
import com.dvp.challenge.domain.repositories.ITicketRepository;
import com.dvp.challenge.infrastructure.exceptions.GenericNotFoundException;
import com.dvp.challenge.infrastructure.mapper.TicketMapper;
import com.dvp.challenge.infrastructure.orm.entities.Status;
import com.dvp.challenge.infrastructure.orm.entities.TicketEntity;
import com.dvp.challenge.infrastructure.orm.repositories.JpaTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TicketRepositoryImpl implements ITicketRepository {
   private final JpaTicketRepository jpaTicketRepository;

   @Override
   @Transactional
   public Ticket create(Ticket ticket) {
      TicketEntity entity = TicketMapper.toEntity(ticket);
      return TicketMapper.toDomain(this.jpaTicketRepository.save(entity));
   }

   @Override
   @Transactional
   public Ticket edit(String id, Ticket ticket) {
      Optional<TicketEntity> entity = this.jpaTicketRepository.findById(id);
      if (entity.isEmpty()) {
         throw new GenericNotFoundException(
               String.format("<TicketRepositoryImpl.edit> ticket id %s not fount in the database", id),
               TicketError.create().notFound());
      }
      TicketEntity toSave = TicketMapper.toEntity(ticket);
      toSave.setId(id);
      toSave.setCreatedAt(entity.get().getCreatedAt());
      return TicketMapper.toDomain(this.jpaTicketRepository.save(toSave));
   }

   @Override
   @Transactional
   public void delete(String id) {
      this.jpaTicketRepository.deleteById(id);
   }

   @Override
   @Transactional(readOnly = true)
   public Ticket findById(String id) {
      Optional<TicketEntity> entity = this.jpaTicketRepository.findById(id);
      if (entity.isEmpty()) {
         throw new GenericNotFoundException(
               String.format("<TicketRepositoryImpl.findById> ticket id %s not found in the database", id),
               TicketError.create().notFound());
      }
      return TicketMapper.toDomain(entity.get());
   }

   @Override
   @Transactional(readOnly = true)
   public List<Ticket> find(int page, int size) {
      Pageable pageable = PageRequest.of(page, size);
      return this.jpaTicketRepository.findAll(pageable)
            .stream()
            .map(TicketMapper::toDomain)
            .toList();
   }

   @Override
   @Transactional(readOnly = true)
   public List<Ticket> findByStateOrUser(String state, String userId) {
      if (state == null && userId == null) {
         return List.of();
      }
      if (state != null && userId != null) {
         return this.jpaTicketRepository.findByStateAndUserId(Status.valueOf(state.toUpperCase()), userId)
               .stream()
               .map(TicketMapper::toDomain)
               .toList();
      }
      if (state != null) {
         return this.jpaTicketRepository.findByState(Status.valueOf(state.toUpperCase()))
               .stream()
               .map(TicketMapper::toDomain)
               .toList();
      }
      return this.jpaTicketRepository.findByUserId(userId)
            .stream()
            .map(TicketMapper::toDomain)
            .toList();
   }
}
