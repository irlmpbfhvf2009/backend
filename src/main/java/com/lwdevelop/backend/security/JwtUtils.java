package com.lwdevelop.backend.security;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.lwdevelop.backend.entity.Member;

import io.jsonwebtoken.*;


@Component
public class JwtUtils  { 
  
  @Value("")
  private String jwtSecret;

  @Value("")
  private int jwtExpirationMs;

  public String generateJwtToken(Member userPrincipal) {
    return generateTokenFromUsername(userPrincipal.getEmail());
  }

  public String generateTokenFromUsername(String username) {
    return Jwts.builder().setSubject(username).setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
        System.out.println("Invalid JWT signature: {}"+ e.getMessage());
    } catch (MalformedJwtException e) {
        System.out.println("Invalid JWT token: {}"+ e.getMessage());
    } catch (ExpiredJwtException e) {
        System.out.println("JWT token is expired: {}"+ e.getMessage());
    } catch (UnsupportedJwtException e) {
        System.out.println("JWT token is unsupported: {}"+ e.getMessage());
    } catch (IllegalArgumentException e) {
        System.out.println("JWT claims string is empty: {}"+ e.getMessage());
    }

    return false;
  }


}
