package com.alinatkachuk.socialnetwork.controller;

import com.alinatkachuk.socialnetwork.model.Comment;
import com.alinatkachuk.socialnetwork.service.CommentServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class CommentController {

    CommentServiceImpl commentServiceImpl;

    @GetMapping(path="{userId}/posts")
    public String createComment (@PathVariable("userId") Long userId,
                                 @PathVariable("postId") Long postId,
                                 Model model) {
        model.addAttribute("comment", new Comment ());
        return "createComment";
    }

    @PostMapping(path="{userId}/posts/new/comment")
    public void savePost (@PathVariable("postId") Long postId,
                          @PathVariable("comment") Comment comment,
                          @PathVariable("userId") Long userId) {
        commentServiceImpl.saveComment (postId, comment);
    }

}
