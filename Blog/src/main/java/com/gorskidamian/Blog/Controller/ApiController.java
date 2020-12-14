package com.gorskidamian.Blog.Controller;


import com.gorskidamian.Blog.Models.Comment;
import com.gorskidamian.Blog.Models.Post;
import com.gorskidamian.Blog.Service.CommentService;
import com.gorskidamian.Blog.Service.PostService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/api/comments/{id}")
    public Comment getComment(@PathVariable String id){
        return cs.getCommentById(id);
    }

    @GetMapping("/api/posts")
    public List<Post> getPosts(){
        return ps.getPosts();
    }

    @RequestMapping("/api/posts/{id}")
    public Map<Post, List<Comment>> getPost(@PathVariable String id){
        HashMap<Post,  List<Comment>> map = new LinkedHashMap<>();
        map.put(ps.getPostById(id), cs.getPostComments(id));

        return map;
    }

    @RequestMapping("/api/postsOnlyId/{id}")
    public Map<Post, List<String>> getPostWithOnlyCommentId(@PathVariable String id){
        HashMap<Post,  List<String>> map = new LinkedHashMap<>();
        map.put(ps.getPostById(id), cs.getPostCommentsId(id));

        return map;
    }

}
