package com.alinatkachuk.socialnetwork.repository;

import com.alinatkachuk.socialnetwork.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findAll();

    List<Like> findAllByPostId(Long postId);
}
