package com.example.blog.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private String author;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
