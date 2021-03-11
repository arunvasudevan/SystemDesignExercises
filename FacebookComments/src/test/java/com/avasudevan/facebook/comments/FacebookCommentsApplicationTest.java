package com.avasudevan.facebook.comments;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.avasdevan.facebook.comments.Comment;
import com.avasdevan.facebook.comments.Post;
import com.avasdevan.facebook.comments.User;

public class FacebookCommentsApplicationTest {

    User user;
    Post post;
    @Before
    public void init() {
        user = new User("Mark");

        post = user.createPost("Post");
    }

    @Test
    public void readAllCommentsPerUser() {
        Optional<Comment> comment1 = user.addComment(post.getId(), "Comment");
        comment1.ifPresent(comment -> user.addReplyToComment(post.getId(), comment.getId(), "Reply Comment"));

        printComments();
    }


    @Test
    public void testUpdateComment() {
        Optional<Comment> comment1 = user.addComment(post.getId(), "Comment");

        comment1.ifPresent(comment -> user.updateComment(post.getId(), comment.getId(), "Updated Comment"));

        printComments();
    }

    @Test
    public void testAddNestingComment() {
        Optional<Comment> comment1 = user.addComment(post.getId(), "Comment");

        Comment replyComment = user.addReplyToComment(post.getId(), comment1.get().getId(), "This is a Reply");

        user.addReplyToComment(post.getId(), replyComment.getId(), "Reply of a Reply");

        comment1.ifPresent(comment -> user.addReplyToComment(post.getId(), comment.getId(), "This is a Another Reply"));

        comment1.ifPresent(comment -> user.deleteComment(post.getId(), comment.getId()));

        printComments();
    }

    private void printComments() {
        user.getPosts().forEach(
            post -> {
                System.out.println("User: " + user.getName() + ", Post: " + post.getDescription());
                post.getComments().forEach(comment -> {
                    System.out.println("\tComment: " + comment.getDescription());
                    comment.getReplies().forEach(
                        replies -> {
                            System.out.println("\t\tReply:" + replies.getDescription());
                        }
                    );
                });
            }
        );
    }
}
