package com.avasdevan.facebook.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class User {

    private final String id;

    private final String name;

    private final List<Post> posts;

    public User(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.posts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Post createPost(String description) {
        Post post = new Post(id, description);
        posts.add(post);
        return post;
    }

    public Optional<Comment> addComment(String postId, String commentDescription) {
        Optional<Post> postToComment = posts.stream().filter(post -> post.getId().equalsIgnoreCase(postId)).findFirst();
        if (postToComment.isPresent()) {
            return Optional.of(postToComment.get().addComment(commentDescription));
        } else {
            System.out.println("Post does not exist for the user");
        }
        return Optional.empty();
    }

    public void updateComment(String postId, String commentId, String commentDescription) {
        posts.stream().filter(post -> post.getId().equalsIgnoreCase(postId)).findFirst().ifPresent(post -> post.updateComment(commentId, commentDescription));
    }

    // When a comment is deleted all the child comments will be added to the root.
    public void deleteComment(String postId, String commentId) {
        posts.stream().filter(post -> post.getId().equalsIgnoreCase(postId)).findFirst().ifPresent(post -> post.deleteComment(commentId));
    }

    public Comment addReplyToComment(String postId, String commentId, String commentDescription) {
        for (Post post1 : posts) {
            if (post1.getId().equalsIgnoreCase(postId)) {
                return post1.addReplyToComment(commentId, commentDescription).get();
            }
        }
        return null;
    }
}
