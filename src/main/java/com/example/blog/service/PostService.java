package com.example.blog.service;

import com.example.blog.dto.PostDto;
import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getAllPosts(){
        List<Post> posts =  StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(Post::getCreationDate).reversed()).collect(Collectors.toList());

        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private PostDto mapToDto(Post post){
        return PostDto.builder().content(post.getContent()).creationDate(post.getCreationDate()).id(post.getId())
                .title(post.getTitle()).build();
    }

}
