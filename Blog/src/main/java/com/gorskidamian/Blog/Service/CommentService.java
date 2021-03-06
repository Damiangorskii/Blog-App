package com.gorskidamian.Blog.Service;


import com.gorskidamian.Blog.Models.Comment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@ImportResource({"classpath:Comments.xml"})
public class CommentService implements CommentManager {

    List<Comment> comments;

    public CommentService(ApplicationContext context){
        this.comments = new ArrayList<>(context.getBeansOfType(Comment.class).values());
    }


    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void addComment(Comment comment, String id) {
        String idx = "" + (comments.size() +1);
        comment.setId(idx);
        comment.setIdPost(id);


        comments.add(new Comment(comment.getId(), comment.getUsername(), comment.getIdPost(), comment.getCommentContent()));
    }



    @Override
    public void deleteComment(String id) {
        Comment commentToRemove = null;
        for (Comment comment: comments){
            if (comment.getId().equals(id)){
                commentToRemove = comment;
            }
        }
        if (commentToRemove != null){
            comments.remove(commentToRemove);
        }
    }

    @Override
    public void deletePostComment(String id) {
        List<Comment> commentsToRemove = new LinkedList<>();
        for (Comment comment: comments){
            if (comment.getIdPost().equals(id)){
                commentsToRemove.add(comment);
            }
        }
        if (!commentsToRemove.isEmpty()){
            for (Comment comment : commentsToRemove){
                comments.remove(comment);
            }
        }
    }


    @Override
    public Comment getCommentById(String id) {
        Comment c = null;
        for (Comment x: comments){
            if (x.getId().equals(id)){
                c = x;
            }
        }
        return c;
    }

    @Override
    public List<Comment> getPostComments(String id) {
        List<Comment> postComments = new LinkedList<>();

        for (Comment c: comments){
            if (c.getIdPost().equals(id)){
                postComments.add(c);
            }
        }

        if (postComments.isEmpty()){
            return null;
        }

        return postComments;
    }

    @Override
    public void editComment(Comment comment, String id) {

        Comment commentToEdit = getCommentById(id);

        commentToEdit.setCommentContent(comment.getCommentContent());
        commentToEdit.setUsername(comment.getUsername());

    }

    @Override
    public List<Comment> getUserComments(String username) {
        List<Comment> userComments = new LinkedList<>();

        for (Comment comment : comments){
            if (comment.getUsername().equals(username)){
                userComments.add(comment);
            }
        }
        if (userComments.isEmpty()){
            return null;
        }

        return userComments;
    }

    @Override
    public HashMap<String, Integer> getUserStatistics() {
        HashMap<String, Integer> userStats = new LinkedHashMap<>();

        List<String> allUsers = new LinkedList<>();

        for (Comment comment : comments){
            allUsers.add(comment.getUsername());
        }


        for (int i=0; i<allUsers.size(); i++){
            int count = userStats.containsKey(allUsers.get(i)) ? userStats.get(allUsers.get(i)) : 0;
            userStats.put(allUsers.get(i), count+1);
        }


        return userStats;
    }

    @Override
    public List<String> getPostCommentsId(String id) {
        List<String> postCommentsIds = new LinkedList<>();
        for (Comment comment : comments){
            if (comment.getIdPost().equals(id)){
                postCommentsIds.add(comment.getId());
            }
        }
        if (!postCommentsIds.isEmpty()){
            return postCommentsIds;
        }
        return null;
    }


}
