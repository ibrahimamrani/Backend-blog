package com.example.blog.resource;

import com.example.blog.dto.CommentDto;
import com.example.blog.dto.PostDto;
import com.example.blog.exception.BlogException;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("API pour la gestion des blogs.")
@RestController
@RequestMapping("/posts")
public class PostResource {

    private final CommentService commentService;

    private final PostService postService;

    public PostResource(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @ApiOperation(value = "Récupérer tous les posts")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @ApiOperation(value = "Récupérer les commentaires d'un post ")
    @GetMapping(value = "/{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getCommentsForPost(@PathVariable("id") Long postId) throws BlogException {
        return commentService.getCommentsForPost(postId);
    }

    @ApiOperation(value = "Ajouter un commentaire pour un post")
    @PostMapping(value = "/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addComment(@PathVariable("id") Long postId, @RequestBody CommentDto commentDto) throws BlogException {
        return commentService.addComment(postId, commentDto);
    }

    @ApiOperation(value = "Modifier un commentaire ")
    @PutMapping(value = "/comments/{id}/{comment}")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@PathVariable("id") Long commentId, @PathVariable("comment") String comment) throws BlogException {
        commentService.updateComment(commentId, comment);
    }

    @ApiOperation(value = "Supprimer un commentaire ")
    @DeleteMapping(value = "/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@PathVariable("id") Long commentId) throws BlogException {
        commentService.deleteComment(commentId);
    }

}
