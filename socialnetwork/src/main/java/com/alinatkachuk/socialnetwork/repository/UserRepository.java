package com.alinatkachuk.socialnetwork.repository;
import com.alinatkachuk.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public List<User> allUsers();

    public User getUserById(Long id);

    @Query(value="Select * from User u where u.email=:email", nativeQuery = true)
    public User getUserByEmail(@Param("email") String email);


}
