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
                String token=request.getHeader("Authorization").substring(6);
                if (!requiresAuthentication(token)){
                    filterChain.doFilter(request,response); // continua con los sgtes filtros
                    return;
                }
                UsernamePasswordAuthenticationToken authenticationToken=null;
                if (jwtUtils.validateToken(token)){
                    Collection<? extends GrantedAuthority> auth =  
                                memberUserDetailsService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(token)).getAuthorities();
                    authenticationToken=new UsernamePasswordAuthenticationToken(jwtUtils.getUserNameFromJwtToken(token),null,auth);
                }
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request,response);
    }
    protected boolean requiresAuthentication(String header){
        return header != null && header.startsWith("Bearer ");
    }
}
