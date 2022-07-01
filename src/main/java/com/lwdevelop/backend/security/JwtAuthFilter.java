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

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    MemberUserDetailsService memberUserDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String header=request.getHeader("Authorization");
                if (!requiresAuthentication(header)){
                    filterChain.doFilter(request,response); // continua con los sgtes filtros
                    return;
                }
                UsernamePasswordAuthenticationToken authenticationToken=null;
                if (jwtService.validate(header)){
        
                    System.out.println("---------Imprimiendo los roles-----------");
                    for (GrantedAuthority auth:jwtService.getRoles(header)) {
                        System.out.println(auth.getAuthority());
                    }
                    authenticationToken=new UsernamePasswordAuthenticationToken(jwtService.getUsername(header),null,jwtService.getRoles(header));
                }
        
                //SecurityContext se encarga de manejar el contexto de seguridad.Lo que hacemoes es asignar el obj authenticationToken dentro del contexto
                //Esto autentica al usuario dentro del request(peticion) ya que no estamos usando sesiones queda autenticado dentro de la solicitud del request
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request,response);
    }
    protected boolean requiresAuthentication(String header){
        return header != null && header.startsWith("Bearer ");
    }
}
