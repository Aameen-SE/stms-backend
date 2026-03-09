package com.aameen.stms.security;

import com.aameen.stms.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository repo;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRepository repo) {
        this.jwtUtil = jwtUtil;
        this.repo = repo;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            email = jwtUtil.extractEmail(token);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            var userOptional = repo.findByEmail(email);

            if (userOptional.isPresent() && jwtUtil.validateToken(token, email)) {

                var user = userOptional.get();

                var authority =
                        new org.springframework.security.core.authority.SimpleGrantedAuthority(user.getRole());

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                Collections.singletonList(authority)
                        );

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
    }

