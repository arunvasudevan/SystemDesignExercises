package com.avasdevan.facebook.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Post {

    private final String id;
    private String description;

    private final String userid;

    private List<Comment> comments;

    public Post(String userid, String description) {
        this.description = description;
        this.userid = userid;
        this.id = UUID.randomUUID().toString();
        this.comments = new ArrayList<>();
    }


    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    Comment addComment(String description) {
        // Create comment with description and add it to comments.
        Comment comment = new Comment(userid, id, description);
        comments.add(comment);
        return comment;
    }

    Optional<Comment> addReplyToComment(String commentId, String description) {
        for (Comment comment : comments) {
            if (comment.getId().equalsIgnoreCase(commentId)) {
                if(!comment.replyToComment(description)) {
                    Comment newComment = new Comment(userid, id, description);
                    comments.add(newComment);
                    return Optional.of(newComment);
                }
                return Optional.of(comment);
            }
        }
        return Optional.empty();
    }

    void deleteComment(String commentId) {

        Optional<Comment> commentToDelete = comments.stream().filter(comment -> comment.getId().equalsIgnoreCase(commentId)).findFirst();

        if(commentToDelete.isEmpty()) {
            System.out.println("Comment ID not present");
            return;
        }

        comments.addAll(commentToDelete.get().getReplies());
        comments.remove(commentToDelete.get());
    }

    void updateComment(String commentId, String description) {
        comments.stream().filter(comment -> comment.getId().equalsIgnoreCase(commentId)).findFirst().ifPresent(comment -> comment.updateComment(description));
    }
}
