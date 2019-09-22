package com.example.blog.service;

import com.example.blog.dto.CommentDto;
import com.example.blog.exception.BlogException;
import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;


    @Test
    public void shouldGetCommentsForPostSuccess() throws BlogException {

        //Given
        given(commentRepository.findByPost(any(Post.class))).willReturn(Arrays.asList(Comment.builder().comment("comment1").build()));
        given(postRepository.findById(anyLong())).willReturn(Optional.of(Post.builder().id(1L).title("title post1").build()));

        //when
        List<CommentDto> commentDtos = commentService.getCommentsForPost(1L);

        //then
        assertNotNull(commentDtos);
        assertFalse(commentDtos.isEmpty());
        assertEquals(commentDtos.get(0).getComment(), "comment1");
    }

    @Test(expected = BlogException.class)
    public void shouldGetCommentsForPostError() throws BlogException {

        //Given
        given(postRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        commentService.getCommentsForPost(1L);

    }

    @Test
    public void shouldAddCommentSuccess() throws BlogException {

        //Given
        given(commentRepository.save(any(Comment.class))).willReturn(Comment.builder().id(1L).comment("comment1").build());
        given(postRepository.findById(anyLong())).willReturn(Optional.of(Post.builder().id(1L).title("title post1").build()));

        //when
        Long commentId = commentService.addComment(1L, CommentDto.builder().comment("comment").author("author").build());

        //then
        assertNotNull(commentId);
        assertEquals(commentId, Long.valueOf(1));
    }

    @Test(expected = BlogException.class)
    public void shouldAddCommentError() throws BlogException {

        //Given
        given(postRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        commentService.addComment(1L, CommentDto.builder().comment("comment").author("author").build());

    }

    @Test
    public void shouldUpdateCommentSuccess() throws BlogException {

        //Given
        given(commentRepository.findById(anyLong())).willReturn(Optional.of(Comment.builder().id(1L).comment("comment1").build()));

        //when
        commentService.updateComment(1L, "comment2");

    }

    @Test(expected = BlogException.class)
    public void shouldUpdateCommentError() throws BlogException {

        //Given
        given(commentRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        commentService.updateComment(1L, "comment2");

    }

    @Test
    public void shouldDeleteCommentSuccess() throws BlogException {

        //Given
        given(commentRepository.findById(anyLong())).willReturn(Optional.of(Comment.builder().id(1L).comment("comment1").build()));

        //when
        commentService.deleteComment(1L);

    }

    @Test(expected = BlogException.class)
    public void shouldDeleteCommentError() throws BlogException {

        //Given
        given(commentRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        commentService.deleteComment(1L);

    }

}
