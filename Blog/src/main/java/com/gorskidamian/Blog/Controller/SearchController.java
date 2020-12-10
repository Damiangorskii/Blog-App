package com.gorskidamian.Blog.Controller;

import com.gorskidamian.Blog.Models.Comment;
import com.gorskidamian.Blog.Models.Post;
import com.gorskidamian.Blog.Service.CommentService;
import com.gorskidamian.Blog.Service.PostService;
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
public class SearchController {

    @Autowired
    CommentService cs;

    @Autowired
    PostService ps;


    @GetMapping("/search")
    public String home(){
        return "search/home";
    }

    @GetMapping("/search/posts")
    public String postSelectSearch(){
        return "search/postSelectSearch";
    }

    @GetMapping("/search/comments")
    public String searchUserComments(Model model){
        model.addAttribute("comment", new Comment());
        return "search/userCommentsForm";
    }

    @PostMapping("/search/comments")
    public String searchedUserComments(Model model, Comment comment){
        model.addAttribute("comments", cs.getUserComments(comment.getUsername()));
        return "search/userCommentsResults";
    }

    @GetMapping("/search/posts/tags")
    public String searchPostsByTags(Model model){
        model.addAttribute("post", new Post());
        return "search/postByTagsForm";
    }

    @PostMapping("/search/posts/tags")
    public String searchedPostsByTags(Model model, Post post){
        model.addAttribute("posts", ps.getPostsByTags(post.getTags()));
        model.addAttribute("userInput", post.getTags());
        return "search/postByTagsResults";
    }

    @GetMapping("/search/posts/author-content")
    public String searchPostsByAuthorOrContent(Model model){
        model.addAttribute("post", new Post());
        return "search/postByAuthorOrContentForm";
    }

    @PostMapping("/search/posts/author-content")
    public String searchedPostsByAuthorOrContent(Model model, Post post){
        model.addAttribute("posts", ps.getPostsByAuthorsOrContent(post.getAuthors()));
        model.addAttribute("userInput", post.getAuthors());
        return "search/postByAuthorOrContentResults";
    }

    @GetMapping("/search/posts/first-author")
    public String searchPostsByFirstAuthor(Model model){
        model.addAttribute("post", new Post());
        return "search/postByFirstAuthorForm";
    }

    @PostMapping("/search/posts/first-author")
    public String searchedPostsByFirstAuthor(Model model, Post post){
        model.addAttribute("posts", ps.getPostsByFirstAuthor(post.getAuthors()));
        model.addAttribute("userInput", post.getAuthors());
        return "search/postByFirstAuthorResults";
    }


}
