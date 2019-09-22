package com.example.blog.repository;

import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class CommentRepositoryIT {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    private static final Long POST_ID = 1L;


    @Test
    public void findByPostTest() {
        Optional<Post> post = postRepository.findById(POST_ID);

        Comment comment = Comment.builder().author("author1").comment("comment1").creationDate(new Date()).post(post.get()).build();
        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findByPost(post.get());
        assertNotNull(comments);
        assertFalse(comments.isEmpty());
        assertEquals(comments.get(0).getAuthor(), "author1");

    }
}
