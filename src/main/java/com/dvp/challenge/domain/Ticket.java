package com.dvp.challenge.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record Ticket(
      String id,

      @NotNull
      @Size(max = 500)
      String description,

      @NotNull
      @NotBlank
      String userId,

      String userName,

      @NotNull
      @NotBlank
      String status,

      LocalDateTime createdAt,

      LocalDateTime updatedAt
) { }
