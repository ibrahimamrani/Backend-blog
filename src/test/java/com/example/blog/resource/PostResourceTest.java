package com.example.blog.resource;

import com.example.blog.dto.CommentDto;
import com.example.blog.dto.PostDto;
import com.example.blog.exception.BlogException;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PostResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    private PostService postService;

    @Before
    public void setUp() {
        Mockito.reset(postService, commentService);
    }

    @Test
    public void shouldReturnAllPost() throws Exception {
        //given
        List<PostDto> postDtos = Arrays.asList(PostDto.builder().id(1L).content("content1").creationDate(new Date()).title("title1").build());

        //when
        when(postService.getAllPosts()).thenReturn(postDtos);

        // then
        mockMvc.perform(get("/posts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].content", is("content1")));
    }

    @Test
    public void shouldReturnCommentsForPost() throws Exception {
        //given
        List<CommentDto> commentDtos = Arrays.asList(CommentDto.builder().id(1L).comment("Comment1").creationDate(new Date()).build());

        //when
        when(commentService.getCommentsForPost(anyLong())).thenReturn(commentDtos);

        // then
        mockMvc.perform(get("/posts/1/comments").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].comment", is("Comment1")));
    }

    @Test
    public void shouldAddComment() throws Exception {
        //given
        CommentDto commentDto = CommentDto.builder().comment("Comment1").author("author1").build();

        //when
        when(commentService.addComment(1L, commentDto)).thenReturn(1L);

        String content = new ObjectMapper().writeValueAsString(commentDto);

        // then
        mockMvc.perform(post("/posts/1/comments")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateComment() throws Exception {


        //when
        doNothing().when(commentService).updateComment(1L, "newComment");


        // then
        mockMvc.perform(put("/posts/comments/1/newComment")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteComment() throws Exception {


        //when
        doNothing().when(commentService).deleteComment(1L);


        // then
        mockMvc.perform(delete("/posts/comments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCommentWithBlogException() throws Exception {

        //when
        doThrow(new BlogException("Error message")).when(commentService).deleteComment(1L);


        // then
        mockMvc.perform(delete("/posts/comments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
