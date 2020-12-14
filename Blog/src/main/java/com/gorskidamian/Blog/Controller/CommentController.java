package com.gorskidamian.Blog.Controller;

import com.gorskidamian.Blog.Models.Comment;
import com.gorskidamian.Blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
public class CommentController {

    @Autowired
    CommentService cs;


    @GetMapping("/comment/comments")
    public String home(Model model) throws ParseException{
        model.addAttribute("comments", cs.getComments());
        return "comment/comments";
    }

    @GetMapping("/comment/create/{postId}")
    public String commentCreate(Model model, @PathVariable String postId){
        model.addAttribute("comment", new Comment());
        return "comment/commentForm";
    }

    @PostMapping("/comment/create/{postId}")
    public String commentCreation(@Valid Comment comment, Errors errors, @PathVariable String postId){
        if(errors.hasErrors()){
            return "comment/commentForm";
        }
        cs.addComment(comment, postId);

        return "redirect:/post/detail/{postId}";
    }

    @GetMapping("/comment/delete/{id}")
    public String deleteComment(@PathVariable String id){
        cs.deleteComment(id);

        return "redirect:/comment/comments";
    }

    @GetMapping("/comment/delete/{postId}/{id}")
    public String deletePostComment(@PathVariable String id, @PathVariable String postId){
        cs.deleteComment(id);

        return "redirect:/post/detail/{postId}";
    }

    @GetMapping("/comment/edit/{id}")
    public String commentEdit(Model model, @PathVariable String id) throws ParseException{
        model.addAttribute("comment", cs.getCommentById(id));
        return "comment/commentEdit";
    }

    @PostMapping("/comment/edit/{id}")
    public String commentEdited(@Valid Comment comment, Errors errors, @PathVariable String id){
        if(errors.hasErrors()){
            return "comment/commentEdit";
        }
        cs.editComment(comment, id);

        return "redirect:/comment/comments";
    }

}
