package com.dvp.challenge.infrastructure.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseError {
   private String code;
   private String error;
}
