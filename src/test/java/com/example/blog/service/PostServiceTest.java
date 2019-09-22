package com.example.blog.service;

import com.example.blog.dto.PostDto;
import com.example.blog.exception.BlogException;
import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;


    @Test
    public void shouldGetAllPost() throws BlogException {

        //Given
        given(postRepository.findAll()).willReturn(Arrays.asList(Post.builder().id(1L).title("title1").content("content1")
                .creationDate(new Date()).build()));

        //when
        List<PostDto> postDtos = postService.getAllPosts();

        //then
        assertNotNull(postDtos);
        assertFalse(postDtos.isEmpty());
        assertEquals(postDtos.get(0).getContent(), "content1");
    }


}
