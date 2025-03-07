package com.dvp.challenge.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record User(
      String id,
      @NotNull
      @NotBlank
      String name,
      @NotNull
      @NotBlank
      String lastName,
      LocalDateTime createdAt,
      LocalDateTime updatedAt
) {
}
