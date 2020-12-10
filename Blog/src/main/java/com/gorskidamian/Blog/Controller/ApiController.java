package com.gorskidamian.Blog.Controller;


import com.gorskidamian.Blog.Models.Comment;
import com.gorskidamian.Blog.Models.Post;
import com.gorskidamian.Blog.Service.CommentService;
import com.gorskidamian.Blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    CommentService cs;

    @Autowired
    PostService ps;

    @GetMapping("/api/comments")
    public List<Comment> getComments(){
        return cs.getComments();
    }

    @GetMapping("/api/posts")
    public List<Post> getPosts(){
        return ps.getPosts();
    }

}
