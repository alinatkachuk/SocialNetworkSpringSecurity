package com.alinatkachuk.socialnetwork.controller;

import com.alinatkachuk.socialnetwork.model.Post;
import com.alinatkachuk.socialnetwork.repository.PostRepository;
import com.alinatkachuk.socialnetwork.service.PostServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class PostController {

    PostServiceImpl postServiceImpl;
    PostRepository postRepository;

    @GetMapping(path="{userId}/posts")
    public String createPost (@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("post", new Post());
        return "createPost";
    }

    @PostMapping(path="{userId}/posts/new")
    public String savePost (@PathVariable("post") Post post,
                            @PathVariable("userId") Long userId) {
        postServiceImpl.insertPost (post, userId);
        return "savePost";
    }

    @GetMapping(path="{userId}/feed")
    public String viewFeed (@PathVariable("userId") Long userId) {
        List<Post> feedPosts = postRepository.findAll();
        Collections.sort(postRepository.findAll());
        return "viewFeed";
    }

}
