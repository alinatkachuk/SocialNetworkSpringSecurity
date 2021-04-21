package com.alinatkachuk.socialnetwork.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

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
//                .csrf().csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasAnyRole(USER.name(), ADMIN.name())
                .antMatchers("/admin/api/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                     .loginPage("/login").permitAll()
                     .defaultSuccessUrl("/home", true)
                     .passwordParameter("password")
                     .usernameParameter("username")
                .and()
                .rememberMe()
                     .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))         //defaults 2 weeks
                     .key("secured")
                .and()
                .logout()
                     .logoutUrl("/logout")
                     .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                     .clearAuthentication(true)
                     .invalidateHttpSession(true)
                     .deleteCookies("JSESSIONID", "remember-me")
                     .logoutSuccessUrl("/login");
    }
}

