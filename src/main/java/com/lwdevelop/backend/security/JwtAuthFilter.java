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

        String header = request.getHeader("Authorization");
        if (!requiresAuthentication(header)) {
            filterChain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = null;
        String token=header.substring(6);   // 篩掉Bearer ey...開始
        if (jwtUtils.validateToken(token)) {
            Collection<? extends GrantedAuthority> auth = memberUserDetailsService
                    .loadUserByUsername(jwtUtils.getUserNameFromJwtToken(token)).getAuthorities();
            authenticationToken = new UsernamePasswordAuthenticationToken(jwtUtils.getUserNameFromJwtToken(token), null,
                    auth);
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);

    }

    protected boolean requiresAuthentication(String header) {
        return header != null && header.startsWith("Bearer ");
    }
}
