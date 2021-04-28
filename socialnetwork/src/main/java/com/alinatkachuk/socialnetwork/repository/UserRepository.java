package com.alinatkachuk.socialnetwork.repository;
import com.alinatkachuk.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public List<User> findAll();

    Optional<User> findById(Long id);

    public User findUserByEmail (String email);

}
