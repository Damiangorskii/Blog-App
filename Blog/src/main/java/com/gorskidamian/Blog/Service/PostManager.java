package com.gorskidamian.Blog.Service;

import com.gorskidamian.Blog.Models.Comment;
import com.gorskidamian.Blog.Models.Post;

import java.util.List;


public interface PostManager {

    List<Post> getPosts();

    void addPost(Post post);

    void deletePost(String id);

    Post getPostById(String id);

    void editPost(Post post, String id);

    List<Post> getPostsByTags(String tag);

    List<Post> getPostsByAuthorsOrContent(String userInput);

}
