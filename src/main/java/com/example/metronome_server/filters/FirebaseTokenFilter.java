package com.example.metronome_server.filters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Firebase Token Filter
 * - verifies Firebase tokens passed in Header 'Authorization'
 */
public class FirebaseTokenFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        //Checks if token is present
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Missing token!");

        try {
            //Extracts the token from the header
            String token = authHeader.substring(7);

            //Verifies token with Firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            Authentication authentication = new UsernamePasswordAuthenticationToken(decodedToken.getUid(), null, new ArrayList<>());

            // Set the authentication in the SecurityContext for subsequent Spring Security checks
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Error! "+ e);
        }
        chain.doFilter(request,response);
    }
}