package com.alinatkachuk.socialnetwork.service;

import com.alinatkachuk.socialnetwork.exception.ResourceNotFoundException;
import com.alinatkachuk.socialnetwork.model.Like;
import com.alinatkachuk.socialnetwork.repository.LikeRepository;
import com.alinatkachuk.socialnetwork.repository.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class LikeServiceImpl implements LikeService{

    LikeRepository likeRepository;
    PostRepository postRepository;

    @Override
    public Like saveLike(Long postId, Like like) {
        return postRepository.findById(postId).map(post -> {
            like.setPost(post);
            return likeRepository.save(like);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}
