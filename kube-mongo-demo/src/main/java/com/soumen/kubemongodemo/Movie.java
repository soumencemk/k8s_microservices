package com.soumen.kubemongodemo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Soumen Karmakar
 * @Date 13/04/2021
 */
@Document(collation = "movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @Field(name = "movie_id")
    public String id;

    @Field(name = "movie_name")
    public String movieName;

}
