package com.example.assignment.part3.controller;


import com.example.assignment.part3.dto.CommentDto;
import com.example.assignment.part3.entity.Comments;
import com.example.assignment.part3.repository.CommentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController

public class CommentController {

    private final CommentRepository repository;

    public CommentController(CommentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/task/{id}/comment")
    public CollectionModel<CommentDto> findAllFor(@PathVariable Long id) {
        List<CommentDto> commentDtos = repository.findByTaskId_Id(id).stream()
                .map(comments -> comments.toDto())
                .collect(toList());

        CollectionModel<CommentDto> result = CollectionModel.of(commentDtos);
        result.add(linkTo(methodOn(CommentController.class).findAllFor(id)).withSelfRel());

        return result;
    }

        @GetMapping("/comment/{id}")
    public ResponseEntity<CommentDto> findById(@PathVariable Long id) {
        Optional<Comments> comment = repository.findById(id);
        return comment.map(value -> ResponseEntity.ok(value.toDto()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
