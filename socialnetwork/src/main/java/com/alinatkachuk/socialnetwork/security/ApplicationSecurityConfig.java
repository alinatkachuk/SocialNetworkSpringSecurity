package com.alinatkachuk.socialnetwork.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.alinatkachuk.socialnetwork.security.ApplicationPermission.*;
import static com.alinatkachuk.socialnetwork.security.ApplicationRole.ADMIN;
import static com.alinatkachuk.socialnetwork.security.ApplicationRole.USER;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails alinaUser = User.builder()
                .username("alina")
                .password(passwordEncoder.encode("password"))
//                .roles(USER.name())
                .authorities(USER.getGrantedAuthorities())
                .build();

        UserDetails nikitaUser = User.builder()
                .username("nikita")
                .password(passwordEncoder.encode("password123"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
                alinaUser,
                nikitaUser
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(USER.name())
                .antMatchers(HttpMethod.DELETE,"/admin/api/**").hasRole(STATISTICS_READ.name())
                .antMatchers(HttpMethod.POST,"/admin/api/**").hasRole(STATISTICS_READ.name())
                .antMatchers(HttpMethod.PUT,"/admin/api/**").hasRole(STATISTICS_READ.name())
                .antMatchers(HttpMethod.GET,"/admin/api/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}

