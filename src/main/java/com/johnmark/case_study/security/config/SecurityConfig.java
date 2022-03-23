package com.johnmark.case_study.security.config;

import com.johnmark.case_study.security.filter.AuthFilter;
import com.johnmark.case_study.security.filter.CustomAuthFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * John Mark A. Fabros
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthFilter customAuthFilter = new CustomAuthFilter(authenticationManagerBean());
        customAuthFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/login/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/login/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/login/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/employees/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/employees/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/v1/employees/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/v1/employees/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/employees").hasAuthority("ROLE_HR");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthFilter);
        http.addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
