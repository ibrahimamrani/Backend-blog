package com.example.blog.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CommentDto {

    private Long id;

    private Date creationDate;

    private String content;

    private String author;
}
