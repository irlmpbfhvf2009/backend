package com.lwdevelop.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.lwdevelop.backend.service.MemberUserDetailsService;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    MemberUserDetailsService memberUserDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String token=request.getHeader("Authorization");
                System.out.println("abccc");
                if (!requiresAuthentication(token)){
                    System.out.println("1");
                    filterChain.doFilter(request,response); // continua con los sgtes filtros
                    return;
                }
                UsernamePasswordAuthenticationToken authenticationToken=null;
                System.out.println("2");
                System.out.println(token);
                token=token.substring(6);
                if (jwtUtils.validateToken(token)){
                    Collection<? extends GrantedAuthority> a =  memberUserDetailsService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(token)).getAuthorities();
                    System.out.println("3");
                    for (GrantedAuthority auth:a) {
                        System.out.println("4");
                        System.out.println(auth.getAuthority());
                    }
                    System.out.println("5");
                    authenticationToken=new UsernamePasswordAuthenticationToken(jwtUtils.getUserNameFromJwtToken(token),null,a);
                }
                System.out.println("6");
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                System.out.println(SecurityContextHolder.getContext().getAuthentication());
               
                filterChain.doFilter(request,response);
    }
    protected boolean requiresAuthentication(String header){
        System.out.println("7");
        return header != null && header.startsWith("Bearer ");
    }
}
