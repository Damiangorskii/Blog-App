package com.gorskidamian.Blog.Models;


import com.gorskidamian.Blog.Validators.Post.Authors;
import com.gorskidamian.Blog.Validators.Post.PostContent;
import com.gorskidamian.Blog.Validators.Post.Tags;
import lombok.*;



@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Post {


    private String id;

    @Authors
    private String authors;

    @PostContent
    private String postContent;

    @Tags
    private String tags;


}