package com.lwdevelop.backend.security;

import java.util.Date;
import org.springframework.stereotype.Component;
import com.lwdevelop.backend.entity.Member;
import io.jsonwebtoken.*;


@Component
public class JwtUtils  { 
  
  private static final String SECRET = "Bearer ";
  private int jwtExpirationMs = 1*60*1000;

  public String generateToken(Member member) {
    return generateTokenFromUsername(member.getEmail());
  }

  public String generateTokenFromUsername(String username) {
    return Jwts.builder().setSubject(username).setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
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
