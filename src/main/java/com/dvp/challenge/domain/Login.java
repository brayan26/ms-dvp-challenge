package com.dvp.challenge.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record Login(
      @NotNull
      @Pattern(regexp = "^[\\w\\-.]+@([\\w-]+\\.){1,2}[\\w-]{2,4}$",
            message = "Invalid username format, [example@example.com, example22.ex@example.com, example@example.com.co"
      )
      String username,

      @NotNull
      @NotBlank(message = "The password field should not be empty")
      String password
) {
}
