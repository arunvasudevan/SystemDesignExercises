package com.avasdevan.facebook.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Comment {

    String id;

    String description;

    List<Comment> replies;

    String userId;

    String postId;

    boolean isParent;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public Comment(String userId, String postId, String description) {
        this.description = description;
        this.userId = userId;
        this.postId = postId;
        this.replies = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
        this.isParent = true;
    }

    public Comment(String userId, String postId, String description, boolean isParent) {
        this.description = description;
        this.userId = userId;
        this.postId = postId;
        this.replies = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
        this.isParent = isParent;
    }

    void updateComment(String description) {
        this.description = description;
    }

    boolean replyToComment(String description) {
        if(!isParent) {
            System.out.println("It is a nested comment");
            return false;
        }
        Comment replyComment = new Comment(this.userId, this.postId, description, false);
        this.replies.add(replyComment);
        return true;
    }

}
