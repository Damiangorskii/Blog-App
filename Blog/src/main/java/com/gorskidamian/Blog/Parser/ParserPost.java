package com.gorskidamian.Blog.Parser;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.gorskidamian.Blog.Models.Post;
import com.gorskidamian.Blog.Models.PostCSV;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class ParserPost {
    public void parse() throws IOException {

        String fileName = "C:\\Users\\user\\Desktop\\UG\\SEM5\\Technologie Java EE\\Projekt Blog\\Blog\\src\\main\\resources\\ManyPostsManyAuthors.csv";
        Path myPath = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy<PostCSV> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(PostCSV.class);
            String[] fields = {"id", "authors", "postContent", "tags"};
            strategy.setColumnMapping(fields);

            CsvToBean<PostCSV> csvToBean = new CsvToBeanBuilder<PostCSV>(br)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<PostCSV> temp = csvToBean.parse();

            List<Post> posts = new LinkedList<>();

            for (PostCSV p : temp){
                Post newPosts = new Post(p.getId(), authorsToString(p.getAuthors()), p.getPostContent(), p.getTags());

                posts.add(newPosts);

            }

            for (Post x : posts){
                System.out.println(x);
            }


            String output = "C:\\Users\\user\\Desktop\\UG\\SEM5\\Technologie Java EE\\Projekt Blog\\Blog\\src\\main\\resources\\ManyPostsManyAuthors.xml";

            File newFile = new File(output);
            Files.deleteIfExists(newFile.toPath());
            newFile.createNewFile();

            FileWriter writer = new FileWriter(output, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            String header =
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                            + " <beans xmlns=\"http://www.springframework.org/schema/beans\"\n"
                            + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                            + " xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\">\n";

            String footer = "\n</beans>";


            bufferedWriter.write(header);


            for (int i=1;i<posts.size(); i++){
                StringBuilder xml = new StringBuilder();
                xml.append("    <bean id=\"post"+posts.get(i).getId()+"\" class=\"com.gorskidamian.Blog.Models.Post\">\n");
                xml.append("        <constructor-arg name=\"id\" value=\""+posts.get(i).getId()+"\"/>\n");
                xml.append("        <constructor-arg name=\"authors\" value=\""+posts.get(i).getAuthors()+"\"/>\n");
                xml.append("        <constructor-arg name=\"postContent\" value=\""+posts.get(i).getPostContent()+"\"/>\n");
                xml.append("        <constructor-arg name=\"tags\" value=\""+posts.get(i).getTags()+"\"/>\n");
                xml.append("    </bean>\n");



                bufferedWriter.write(xml.toString());
            }


            bufferedWriter.write(footer);
            bufferedWriter.close();

        }
    }


    public static String authorsToString(String a){


            String text = a;

            text = text.replaceAll("\"authors\"", "");


            int N = StringUtils.countMatches(text, ":");
            String authors = "";

            for(int j = 0; j < N; j++) {
                authors = authors + text.substring(text.indexOf(':') + 2, text.indexOf('}') - 1);
                if (j<N-1){
                    authors = authors + ", ";
                }
                text = text.substring(text.indexOf('}') + 1);
                System.out.println(authors);
            }

        return authors;
    }
}