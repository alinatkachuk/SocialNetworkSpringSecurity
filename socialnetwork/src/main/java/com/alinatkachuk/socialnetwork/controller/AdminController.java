package com.alinatkachuk.socialnetwork.controller;

import org.springframework.web.bind.annotation.*;
import com.alinatkachuk.socialnetwork.model.Post;
import com.alinatkachuk.socialnetwork.model.User;
import com.alinatkachuk.socialnetwork.repository.PostRepository;
import com.alinatkachuk.socialnetwork.repository.UserRepository;

import java.util.*;

@RestController
@RequestMapping("admin/api/v1/statistics")
public class AdminController {

    private static final List<User> USERS = Arrays.asList(
            new User (1L, "Alina"),
            new User (2L, "Nikita")
    );

    private static final List<String> reviewPeriods = Arrays.asList("day", "week", "month", "half a year", "year");

    private PostRepository postRepository;
    private UserRepository userRepository;

    @GetMapping
    public static List<String> getReviewPeriodsForStatistics() {
        System.out.println("Review period: ");
        return reviewPeriods;
    }

    @PostMapping
    public void viewStatistics(@RequestBody String reviewPeriod) {
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
        int allPosts = (postRepository.findAllByPublicationDateAfterAndPublicationDateBefore(beginningOfPeriod, endOfPeriod)).size();
        long millis = beginningOfPeriod.getTime().getTime() - endOfPeriod.getTime().getTime();
        int days = (int) millis / 3_600_000 * 24;
        float averageNumberOfPosts = (float) allPosts / days;

        List<User> allUsersForTenUsersWithMaxNumberOfPosts =userRepository.findAll();
        int numberOfLastUserInList = allUsersForTenUsersWithMaxNumberOfPosts.size ()-1;
        allUsersForTenUsersWithMaxNumberOfPosts.sort(Comparator.comparingInt (user -> user.getPosts ().size ()));
        List<User> tenUsersWithMaxNumberOfPosts=allUsersForTenUsersWithMaxNumberOfPosts.subList (numberOfLastUserInList-9, numberOfLastUserInList);

        List <Post> allPostsForStatistics = postRepository.findAllByPublicationDateAfterAndPublicationDateBefore (beginningOfPeriod, endOfPeriod);
        int numberOfLastPostInList = allPostsForStatistics.size ()-1;
        allPostsForStatistics.sort(Comparator.comparingInt (user -> user.getLikes ().size ()));
        List<Post> tenMostLikedPosts=allPostsForStatistics.subList (numberOfLastPostInList-9, numberOfLastPostInList);

        allPostsForStatistics.sort(Comparator.comparingInt (user -> user.getComments ().size ()));
        List<Post> tenMostCommentedPosts=allPostsForStatistics.subList (numberOfLastPostInList-9, numberOfLastPostInList);

    }

}
