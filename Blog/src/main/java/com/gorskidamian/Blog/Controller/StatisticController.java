package com.gorskidamian.Blog.Controller;

import com.gorskidamian.Blog.Service.CommentService;
import com.gorskidamian.Blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StatisticController {

    @Autowired
    CommentService cs;

    @Autowired
    PostService ps;


    @GetMapping("/statistics")
    public String home(){
        return "statistics/home";
    }

    @GetMapping("/statistics/users")
    public String getUsersStatistics(Model model){
        model.addAttribute("statistics", cs.getUserStatistics());
        return "statistics/userStatistics";
    }

    @GetMapping("/statistics/authors")
    public String getAuthorsStatistics(Model model){
        model.addAttribute("statistics", ps.getAuthorStatistics());
        return "statistics/authorStatistics";
    }



}
