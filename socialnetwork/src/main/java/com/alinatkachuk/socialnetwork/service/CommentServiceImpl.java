package com.alinatkachuk.socialnetwork.service;

import com.alinatkachuk.socialnetwork.exception.ResourceNotFoundException;
import com.alinatkachuk.socialnetwork.model.Comment;

import com.alinatkachuk.socialnetwork.repository.CommentRepository;
import com.alinatkachuk.socialnetwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    PostRepository postRepository;

    CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment saveComment(Long postId, Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @Override
    public Comment editComment(Long commentId, Comment comment) {
        return commentRepository.findById(commentId).map(updatedComment -> {
            updatedComment.setText(comment.getText());
            return commentRepository.save(updatedComment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    @Override
    public ResponseEntity<?> deleteComment(Long commentId) {
        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
    }
}
