package com.alinatkachuk.socialnetwork.service;

import com.alinatkachuk.socialnetwork.model.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    Post insertPost(Post post,Long UserId);

    Post editPost(Long postId, Post post);

    ResponseEntity<?> deletePost(Long postId);

}
