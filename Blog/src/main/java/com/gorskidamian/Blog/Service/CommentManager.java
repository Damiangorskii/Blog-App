package com.gorskidamian.Blog.Service;

import com.gorskidamian.Blog.Models.Comment;

import java.util.HashMap;
import java.util.List;


public interface CommentManager {

    List<Comment> getComments();

    void addComment(Comment comment, String id);

    void deleteComment(String id);

    Comment getCommentById(String id);

    List<Comment> getPostComments(String id);

    void editComment(Comment comment, String id);

    List<Comment> getUserComments(String username);

    HashMap<String, Integer> getUserStatistics();

}
