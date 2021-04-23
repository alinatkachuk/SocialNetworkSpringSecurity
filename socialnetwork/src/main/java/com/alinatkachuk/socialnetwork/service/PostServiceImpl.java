package com.alinatkachuk.socialnetwork.service;

import com.alinatkachuk.socialnetwork.exception.ResourceNotFoundException;
import com.alinatkachuk.socialnetwork.model.Post;
import com.alinatkachuk.socialnetwork.model.UserProfile;
import com.alinatkachuk.socialnetwork.repository.PostRepository;
import com.alinatkachuk.socialnetwork.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    UserProfileRepository userProfileRepository;

    PostRepository postRepository;

    @Autowired
    public PostServiceImpl(UserProfileRepository userProfileRepository, PostRepository postRepository) {
        this.userProfileRepository = userProfileRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Post insertPost(Post post, Long userProfileId) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userProfileId);
        if(userProfile != null) {
            post.setUserProfile(userProfile.get());
            return post;
        }
        else{throw new ResourceNotFoundException ("User Profile Is Not Found");
        }
    }

    @Override
    public Post editPost(Long postId, Post post) {
        return postRepository.findById(postId).map(updatedPost -> {
            updatedPost.setTitle(post.getTitle());
            updatedPost.setDescription(post.getDescription());
            updatedPost.setContent(post.getContent());
            return postRepository.save(updatedPost);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @Override
    public ResponseEntity<?> deletePost(Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}
