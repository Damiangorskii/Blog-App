package com.gorskidamian.Blog.Parser;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.gorskidamian.Blog.Models.Comment;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParserComment {
    public void parse() throws IOException {

        String fileName = "C:\\Users\\user\\Desktop\\UG\\SEM5\\Technologie Java EE\\Projekt Blog\\Blog\\src\\main\\resources\\Comments.csv";
        Path myPath = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy<Comment> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Comment.class);
            String[] fields = {"id", "username", "idPost", "commentContent"};
            strategy.setColumnMapping(fields);

            CsvToBean<Comment> csvToBean = new CsvToBeanBuilder<Comment>(br)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Comment> comments = csvToBean.parse();

            String output = "C:\\Users\\user\\Desktop\\UG\\SEM5\\Technologie Java EE\\Projekt Blog\\Blog\\src\\main\\resources\\Comments.xml";

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



            for (int i=1;i<comments.size(); i++){
                String xml = "    <bean id=\"comment"+comments.get(i).getId()+"\" class=\"com.gorskidamian.Blog.Models.Comment\">\n" +
                        "        <constructor-arg name=\"id\" value=\""+comments.get(i).getId()+"\"/>\n"+
                        "        <constructor-arg name=\"username\" value=\""+comments.get(i).getUsername()+"\"/>\n" +
                        "        <constructor-arg name=\"idPost\" value=\""+comments.get(i).getIdPost()+"\"/>\n" +
                        "        <constructor-arg name=\"commentContent\" value=\""+comments.get(i).getCommentContent()+"\"/>\n" +
                        "    </bean>\n";

                bufferedWriter.write(xml);
                System.out.println(comments.get(i).toString());
            }


            bufferedWriter.write(footer);
            bufferedWriter.close();
        }

    }
}