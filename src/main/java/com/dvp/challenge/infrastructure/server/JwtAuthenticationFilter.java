package com.dvp.challenge.infrastructure.server;

import com.dvp.challenge.infrastructure.errors.AuthenticationError;
import com.dvp.challenge.infrastructure.exceptions.GenericUnauthorizedException;
import com.dvp.challenge.infrastructure.utils.JwtDecoderUtil;
import com.dvp.challenge.infrastructure.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
   private static final String PRINCIPAL = "health";
   private static final String HEADER_STRING = "Authorization";
   private static final String TOKEN_PREFIX = "Bearer";
   private static final String HEALTH_URL_PREFIX = "/info";
   private static final String LOGIN_URL_PREFIX = "/doLogin";
   private static final String ACTUATOR_URL_PREFIX = "/actuator";
   private static final String SWAGGER_URL_PREFIX = "swagger";
   private static final String API_SWAGGERL_PREFIX = "api-docs";
   private static final String H2_URL_PREFIX = "/h2";

   @Autowired
   private JwtUtils jwtUtils;

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String authorizationHeader = request.getHeader(HEADER_STRING);
      String urlRequest = request.getRequestURL().toString();

      UsernamePasswordAuthenticationToken authentication;

      if (urlRequest.contains(HEALTH_URL_PREFIX) || urlRequest.contains(LOGIN_URL_PREFIX)
            || urlRequest.contains(SWAGGER_URL_PREFIX) || urlRequest.contains(API_SWAGGERL_PREFIX)
            || urlRequest.contains(H2_URL_PREFIX) || urlRequest.contains(ACTUATOR_URL_PREFIX)) {
         List<GrantedAuthority> grantedAuths = AuthorityUtils
               .commaSeparatedStringToAuthorityList("public");
         authentication = new UsernamePasswordAuthenticationToken(PRINCIPAL, null, grantedAuths);

      } else {

         if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            throw new GenericUnauthorizedException(
                  String.format("<JwtAuthenticationFilter - doFilterInternal> AuthorizationHeader '%s' is not accepted",
                        authorizationHeader),
                  AuthenticationError.create().unauthorizedError().build()
            );
         }

         authentication = (UsernamePasswordAuthenticationToken) getAuthentication(request);
      }
      SecurityContextHolder.getContext().setAuthentication(authentication);
      filterChain.doFilter(request, response);
   }

   private Authentication getAuthentication(HttpServletRequest request) {
      String token = request.getHeader(HEADER_STRING);
      SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
      if (token == null) {
         throw new GenericUnauthorizedException(
               "<JwtAuthenticationFilter - getAuthentication> Invalid token, null",
               AuthenticationError.create().invalidTokenError().build());
      }

      String authToken = token.replace(TOKEN_PREFIX, "").trim();
      if (!jwtUtils.validateJwtToken(authToken)) {
         throw new GenericUnauthorizedException(
               String.format("<JwtAuthenticationFilter - getAuthentication> Token JWT not valid: '%s'", token),
               AuthenticationError.create().invalidTokenError().build());
      }

      JwtDecoderUtil decoder = new JwtDecoderUtil(authToken);
      if ((decoder.getPayload().getExp() * 1000) < (System.currentTimeMillis())) {
         throw new GenericUnauthorizedException(
               String.format("<JwtAuthenticationFilter - getAuthentication> Expired token '%s'",
                     sf.format(decoder.getPayload().getExp() * 1000)),
               AuthenticationError.create().expiredTokenError().build());
      }

      List<GrantedAuthority> grantedAuths = AuthorityUtils
            .commaSeparatedStringToAuthorityList(decoder.getPayload().getAuthorities().toString());
      return new UsernamePasswordAuthenticationToken(decoder.getPayload().getSub(), null, grantedAuths);
   }
}
