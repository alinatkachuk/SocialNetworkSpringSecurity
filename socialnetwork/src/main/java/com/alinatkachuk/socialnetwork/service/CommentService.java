package com.alinatkachuk.socialnetwork.service;

import com.alinatkachuk.socialnetwork.model.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    Comment saveComment(Long postId,Comment comment);

    Comment editComment(Long commentId,Comment comment);

    ResponseEntity<?> deleteComment(Long commentId);

}
