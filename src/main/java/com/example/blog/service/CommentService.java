package com.example.blog.service;

import com.example.blog.dto.CommentDto;
import com.example.blog.exception.BlogException;
import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    /**
     * Returns a list of comments for postId.
     *
     * @param postId id of the post
     * @return list of comments sorted by creation date descending
     * @throws BlogException if there is no post for postId
     */
    public List<CommentDto> getCommentsForPost(Long postId) throws BlogException {

        log.info("Start get comments for post {} ", postId);
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()) {
            log.error("Post not found for postId {} ", postId);
            throw new BlogException("Le post spécifié en parametre n'existe pas ");
        }

        List<Comment> comments = commentRepository.findByPost(post.get());
        List<CommentDto> commentDtos = comments.stream().sorted(Comparator.comparing(Comment::getCreationDate).reversed())
                .map(c -> CommentDto.builder().author(c.getAuthor()).content(c.getContent()).id(c.getId())
                        .creationDate(c.getCreationDate()).build()).collect(Collectors.toList());

        log.info("End get comments for post {} ", postId);
        return commentDtos;

    }

    /**
     * Create a new comment
     *
     * @param commentDto new comment
     * @param postId     id of post
     * @return id of the created comment
     * @throws BlogException if there is no post for passed postId
     */
    public Long addComment(Long postId, CommentDto commentDto) throws BlogException {
        log.info("Start add comment for post {} ", postId);

        Comment comment = Comment.builder().content(commentDto.getContent()).author(commentDto.getAuthor()).build();
        comment.setCreationDate(new Date());

        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()) {
            comment.setPost(post.get());
        } else {
            log.error("Post not found for postId {} ", postId);
            throw new BlogException("Le post spécifié en parametre n'existe pas ");
        }

        comment = commentRepository.save(comment);

        log.info("End add comment for post {} ", postId);

        return comment.getId();
    }


    /**
     * Update comment
     *
     * @param commentId id of comment
     * @param content   text content of comment
     * @throws BlogException throw blog exception if comment not found
     */
    public void updateComment(Long commentId, String content) throws BlogException {

        log.info("Start update comment for comment id {} ", commentId);

        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (commentOptional.isPresent()) {
            Comment commentEntity = commentOptional.get();
            commentEntity.setContent(content);
            commentRepository.save(commentEntity);
        } else {
            log.error("Comment not found for id {} ", commentId);
            throw new BlogException("Le commentaire spécifié en parametre n'existe pas ");
        }

        log.info("End update comment for comment id {} ", commentId);
    }

    /**
     * Delete existing comment by id
     *
     * @param commentId id of comment
     * @throws BlogException throw blog exception if comment not found
     */
    public void deleteComment(Long commentId) throws BlogException {
        log.info("Start delete comment for comment id {} ", commentId);

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            commentRepository.delete(commentOptional.get());
        } else {
            log.error("Comment not found for id {} ", commentId);
            throw new BlogException("Le commentaire spécifié en parametre n'existe pas ");
        }
        log.info("End update comment for comment id {} ", commentId);
    }
}
