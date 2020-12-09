package com.gorskidamian.Blog;


import com.gorskidamian.Blog.Parser.ParserComment;
import com.gorskidamian.Blog.Parser.ParserPost;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import java.io.IOException;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) throws IOException {
		ParserPost parserPost = new ParserPost();
		parserPost.parse();
		ParserComment parserComment = new ParserComment();
		parserComment.parse();
		ApplicationContext context = SpringApplication.run(BlogApplication.class, args);
	}

}
