package com.alinatkachuk.socialnetwork.service;

import com.alinatkachuk.socialnetwork.model.Like;

public interface LikeService {

    Like saveLike(Long postId, Like like);
}
