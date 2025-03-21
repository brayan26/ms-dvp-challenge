package com.dvp.challenge.infrastructure.utils;

import com.auth0.jwt.exceptions.JWTDecodeException;

public class TokenUtil {

   /**
    * Splits the given token on the "." chars into a String array with 3 parts.
    *
    * @param token the string to split.
    * @return the array representing the 3 parts of the token.
    * @throws JWTDecodeException if the Token doesn't have 3 parts.
    */
   public static String[] splitToken(String token) {
      String[] parts = token.split("\\.");
      if (parts.length == 2 && token.endsWith(".")) {
         //Tokens with alg='none' have empty String as Signature.
         parts = new String[]{parts[0], parts[1], ""};
      }
      if (parts.length != 3) {
         throw new JWTDecodeException(String.format("The token was expected to have 3 parts, but got %s.", parts.length));
      }
      return parts;
   }
}