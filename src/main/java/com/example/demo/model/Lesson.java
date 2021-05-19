package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Lesson {
    private final UUID id; //UUID gives us a random accpected ID

    @NotBlank //dont want this to be blank, has not be a name, if we used @Notnull it could still be an empty stirng
    private final String name;
    private final String youtubeLink;
    private final String tagLine;

    public Lesson(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("youtubelink") String youtubeLink,
                  @JsonProperty("tagline") String tagLine
    ){
        this.id = id;
        this.name = name;
        this.youtubeLink = youtubeLink;
        this.tagLine  = tagLine;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYoutubeLink(){return youtubeLink;}

    public String getTagLine(){return tagLine;}
}
