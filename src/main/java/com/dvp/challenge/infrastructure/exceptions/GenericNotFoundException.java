package com.dvp.challenge.infrastructure.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class GenericNotFoundException extends RuntimeException {
   private Object error;

   public GenericNotFoundException(String message, Object error) {
      super(message);
      this.error = error;
   }
}
