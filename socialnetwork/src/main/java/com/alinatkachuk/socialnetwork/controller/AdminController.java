package com.alinatkachuk.socialnetwork.controller;

import com.alinatkachuk.socialnetwork.model.Post;
import com.alinatkachuk.socialnetwork.model.User;
import com.alinatkachuk.socialnetwork.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("admin/api/v1/statistics")
public class AdminController {

    private static final List<User> USERS = Arrays.asList(
            new User (1L, "Alina"),
            new User (2L, "Nikita")
    );

    private static final List<String> reviewPeriods = Arrays.asList("day", "week", "month", "half a year", "year");

    private PostRepository postRepository;

    @GetMapping
    public static List<String> getReviewPeriodsForStatistics() {
        System.out.println("Review period: ");
        return reviewPeriods;
    }

    @PostMapping
    public void registerNewUser(@RequestBody String reviewPeriod) {
        System.out.println("Statistics for the specified period: ");
        Calendar beginningOfPeriod = Calendar.getInstance();
        Calendar endOfPeriod = Calendar.getInstance();
        beginningOfPeriod.getTime();
        endOfPeriod.getTime();
        switch (reviewPeriod) {
            case  ("day"):
                beginningOfPeriod.roll (Calendar.DATE, false);
                break;
            case ("week"):
                beginningOfPeriod.roll (Calendar.DAY_OF_WEEK, false);
                break;
            case ("month"):
                beginningOfPeriod.roll (Calendar.MONTH, false);
                break;
            case ("half a year"):
                beginningOfPeriod.roll ((Calendar.MONTH)*6, false);
                break;
            case ("year"):
                beginningOfPeriod.roll (Calendar.YEAR, false);
                break;
        }
        int allPosts=0; // = (postRepository.findAll()).stream().filter (post -> post.getPublicationDate().before(beginningOfPeriod)).count ();

        double averageNumberOfPosts = allPosts/(beginningOfPeriod.get(Calendar.DAY_OF_YEAR) - endOfPeriod.get(Calendar.DAY_OF_YEAR));

        List<User> tenUsersWithMaxNumberOfPosts;

        List<Post> tenMostLikedPosts; //   =(postRepository.findAll()).stream().sorted (Post::compareByLikes).limit (10);

        List<Post> tenMostCommentedPosts; //   =(postRepository.findAll()).stream().sorted (???).limit (10);

    }

//    public int compareByLikes (Post p2) {
//        return likes<p2.getLikes ()?1:(likes==p2.getLikes ()?0:-1);
//    }

}
