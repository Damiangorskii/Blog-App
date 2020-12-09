package com.gorskidamian.Blog.Service;


import com.gorskidamian.Blog.Models.Comment;
import com.gorskidamian.Blog.Models.Post;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@ImportResource({"classpath:ManyPostsManyAuthors.xml"})
public class PostService implements PostManager {

    List<Post> posts;

    public PostService(ApplicationContext context){
        this.posts = new ArrayList<>(context.getBeansOfType(Post.class).values());
    }


    @Override
    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public void addPost(Post post) {
        String idx = "" + (posts.size() +1);
        post.setId(idx);

        posts.add(new Post(post.getId(), post.getAuthors(), post.getPostContent(), post.getTags()));
    }



    @Override
    public void deletePost(String id) {
        Post postToRemove = null;
        for (Post post: posts){
            if (post.getId().equals(id)){
                postToRemove = post;
            }
        }
        if (postToRemove != null){
            posts.remove(postToRemove);
        }
    }


    @Override
    public Post getPostById(String id) {
        Post p = null;
        for (Post x: posts){
            if (x.getId().equals(id)){
                p = x;
                return p;
            }
        }
        return null;
    }

    @Override
    public void editPost(Post post, String id) {

        Post postToEdit = getPostById(id);

        postToEdit.setAuthors(post.getAuthors());
        postToEdit.setPostContent(post.getPostContent());
        postToEdit.setTags(post.getTags());

    }

    @Override
    public List<Post> getPostsByTags(String tag) {
        List<Post> postsTags = new LinkedList<>();

        for (Post post : posts){
            if (post.getTags().contains(tag)){
                postsTags.add(post);
            }
        }
        if (postsTags.isEmpty()){
            return null;
        }

        return postsTags;
    }

    @Override
    public List<Post> getPostsByAuthorsOrContent(String userInput) {
        List<Post> postsAuthorsOrContent = new LinkedList<>();

        for (Post post : posts){
            if ((post.getAuthors().contains(userInput)) || (post.getPostContent().contains(userInput))){
                postsAuthorsOrContent.add(post);
            }
        }
        if (postsAuthorsOrContent.isEmpty()){
            return null;
        }

        return postsAuthorsOrContent;
    }


}
