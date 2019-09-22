package com.example.blog.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PostDto {

    private Long id;

    private String title;

    private String content;

    private Date creationDate;
}
