package com.fjalvarez.review;

import com.fjalvarez.user.User;
import com.fjalvarez.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;

@RepositoryEventHandler(Review.class)
public class ReviewEventHandler {

    @Autowired
    private final UserRepository users;

    public ReviewEventHandler(UserRepository users) {
        this.users = users;
    }

    @HandleBeforeCreate
    public void addReviewerBasedOnLoggedInUser(Review review) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByUsername(username);
        review.setReviewer(user);
    }
}
