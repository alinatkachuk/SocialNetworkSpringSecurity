package com.alinatkachuk.socialnetwork.controller;

import com.alinatkachuk.socialnetwork.model.Comment;
import com.alinatkachuk.socialnetwork.model.Like;
import com.alinatkachuk.socialnetwork.service.LikeServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class LikeController {

    LikeServiceImpl likeServiceImpl;

    @GetMapping(path="{userId}/posts")
    public String likePost (@PathVariable("userId") Long userId,
                                 @PathVariable("postId") Long postId,
                                 Model model) {
        model.addAttribute("like", new Like ());
        return "createComment";
    }

    @PostMapping(path="{userId}/posts/new/like")
    public void savePost (@PathVariable("postId") Long postId,
                          @PathVariable("like") Like like,
                          @PathVariable("userId") Long userId) {
        likeServiceImpl.saveLike (postId, like);
    }
}
