package com.dvp.challenge.domain;

import java.util.UUID;

public record Auth(
      UUID id,
      String username,
      String password
) {
}
