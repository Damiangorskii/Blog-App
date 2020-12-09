package com.gorskidamian.Blog.Models;


import com.gorskidamian.Blog.Validators.Comments.CommentContent;
import com.gorskidamian.Blog.Validators.Comments.Username;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Comment {

    private String id;

    @Username
    private String username;


    private String idPost;

    @CommentContent
    private String commentContent;


}
