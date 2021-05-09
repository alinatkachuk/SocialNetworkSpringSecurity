package com.alinatkachuk.socialnetwork.auth;

import com.alinatkachuk.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserDao extends JpaRepository<Long, User> {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
