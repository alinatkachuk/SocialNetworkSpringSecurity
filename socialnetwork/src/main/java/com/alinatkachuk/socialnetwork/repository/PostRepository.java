package com.alinatkachuk.socialnetwork.repository;

import com.alinatkachuk.socialnetwork.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    public List<Post> findByUserProfileId(Long userProfileId);

}
