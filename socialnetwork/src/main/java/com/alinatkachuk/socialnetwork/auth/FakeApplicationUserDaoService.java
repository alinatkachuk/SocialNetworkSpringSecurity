package com.alinatkachuk.socialnetwork.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.alinatkachuk.socialnetwork.security.ApplicationRole.ADMIN;
import static com.alinatkachuk.socialnetwork.security.ApplicationRole.USER;

@Repository("fake")
public class FakeApplicationUserDaoService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList (
                new ApplicationUser(
                        ADMIN.getGrantedAuthorities(),
                        "nikita",
                        passwordEncoder.encode("password123"),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        USER.getGrantedAuthorities(),
                        "alina",
                        passwordEncoder.encode("password"),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }
}
