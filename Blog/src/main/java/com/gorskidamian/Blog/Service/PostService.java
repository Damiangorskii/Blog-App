package com.gorskidamian.Blog.Service;


import com.gorskidamian.Blog.Models.Post;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<Post> getPostsByFirstAuthor(String userInput) {
        List<Post> postsFirstAuthor = new LinkedList<>();


        for (Post post : posts){
            if (post.getAuthors().substring(0, post.getAuthors().indexOf(',')+1).contains(userInput)){
                String firstAuthor = post.getAuthors().substring(0, post.getAuthors().indexOf(','));
                System.out.println(firstAuthor);
                postsFirstAuthor.add(post);
            }
        }
        if (postsFirstAuthor.isEmpty()){
            return null;
        }

        return postsFirstAuthor;
    }

    @Override
    public HashMap<String, Integer> getAuthorStatistics() {
        HashMap<String, Integer> authorStats = new LinkedHashMap<>();

        List<String> allAuthors = new LinkedList<>();

        for (Post post : posts){
            String text = post.getAuthors();
            int N = StringUtils.countMatches(text, " ");
            String authorTemp = "";

            for(int j = 0; j < N; j++) {
                if (!text.contains(",")){
                    allAuthors.add(text);
                    break;
                }
                authorTemp = text.substring(0, text.indexOf(','));
                text = text.substring(text.indexOf(',')+2);
                allAuthors.add(authorTemp);
            }
        }


        for (int i=0; i<allAuthors.size(); i++){
            int count = authorStats.containsKey(allAuthors.get(i)) ? authorStats.get(allAuthors.get(i)) : 0;
            authorStats.put(allAuthors.get(i), count+1);
        }


        return authorStats;
    }
}
