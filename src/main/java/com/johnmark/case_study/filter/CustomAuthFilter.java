package com.johnmark.case_study.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * John Mark A. Fabros
 * */


public class CustomAuthFilter extends UsernamePasswordAuthenticationFilter {

private final AuthenticationManager authentication;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        logger.info("Name is: "+ name);
        logger.info("Password is: "+ password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, password);
        return authentication.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("temp".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

    }  public CustomAuthFilter(AuthenticationManager authentication) {
        this.authentication = authentication;
    }



}
