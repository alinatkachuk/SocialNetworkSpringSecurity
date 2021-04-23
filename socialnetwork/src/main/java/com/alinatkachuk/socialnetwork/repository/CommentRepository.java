package com.alinatkachuk.socialnetwork.repository;

import com.alinatkachuk.socialnetwork.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

        List<Comment> getCommentsByPostId(Long id);

}

