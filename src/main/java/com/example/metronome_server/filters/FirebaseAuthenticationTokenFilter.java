/*
package com.example.metronome_server.filters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class FirebaseAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String idToken = request.getHeader("Authorization");

        try {
            FirebaseToken decodeToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            Authentication authentication = new UsernamePasswordAuthenticationToken(decodeToken.getUid(), null, new ArrayList<>());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);
    }
}
*/