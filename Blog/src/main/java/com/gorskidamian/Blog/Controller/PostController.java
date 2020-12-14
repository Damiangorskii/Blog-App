package com.gorskidamian.Blog.Controller;

import com.gorskidamian.Blog.Models.Post;
import com.gorskidamian.Blog.Service.CommentService;
import com.gorskidamian.Blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.text.ParseException;



@Controller
public class PostController {


    @Autowired
    PostService ps;

    @Autowired
    CommentService cs;


    @GetMapping("/post/posts")
    public String home(Model model) throws ParseException{
        model.addAttribute("posts", ps.getPosts());
        return "post/posts";
    }

    @GetMapping("/post/create")
    public String postCreate(Model model){
        model.addAttribute("post", new Post());
        return "post/postForm";
    }

    @PostMapping("/post/create")
    public String postCreation(@Valid Post post, Errors errors){
        if(errors.hasErrors()){
            return "post/postForm";
        }
        ps.addPost(post);

        return "redirect:/post/posts";
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable String id){
        ps.deletePost(id);
        cs.deletePostComment(id);

        return "redirect:/post/posts";
    }


    @GetMapping("/post/detail/{id}")
    public String postDetailInfo(Model model, @PathVariable String id) throws ParseException{
        model.addAttribute("postDetail", ps.getPostById(id));
        model.addAttribute("comments", cs.getPostComments(id));
        return "post/postDetail";
    }


    @GetMapping("/post/edit/{id}")
    public String postEdit(Model model, @PathVariable String id) throws ParseException{
        model.addAttribute("post", ps.getPostById(id));
        return "post/postEdit";
    }

    @PostMapping("/post/edit/{id}")
    public String postEdited(@Valid Post post, Errors errors,  @PathVariable String id){
        if(errors.hasErrors()){
            return "post/postEdit";
        }
        ps.editPost(post, id);

        return "redirect:/post/detail/{id}";
    }








}
