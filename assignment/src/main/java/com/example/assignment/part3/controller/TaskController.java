package com.example.assignment.part3.controller;


import com.example.assignment.part3.dto.CommentDto;
import com.example.assignment.part3.dto.TaskDto;
import com.example.assignment.part3.entity.Comments;
import com.example.assignment.part3.entity.Tasks;
import com.example.assignment.part3.repository.CommentRepository;
import com.example.assignment.part3.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskRepository repository;
    private final CommentRepository commentRepository;

    @Autowired
    public TaskController(TaskRepository repository, CommentRepository commentRepository) {
        this.repository = repository;
        this.commentRepository = commentRepository;
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long id) {
        Optional<Tasks> task = repository.findById(id);

        return task.map(value -> {
            TaskDto taskDto = value.toDto().addSelfLink().addCommentsLink();
            if (value.hasComments()) {
                taskDto.setComments(value.getComments().stream().map(Comments::toDto).collect(Collectors.toList()));
            }
            return ResponseEntity.ok(taskDto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> modify(@PathVariable Long id, @RequestBody TaskDto dto) {
        Optional<Tasks> found = repository.findById(id);

        if (found.isPresent()) {
            Tasks task = found.get();
            task.update(dto);
            repository.save(task);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Tasks> task = repository.findById(id);
        if (task.isPresent()) {
            repository.delete(task.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public CollectionModel<TaskDto> findAll() {
        List<TaskDto> taskDtos = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(task -> task.toDto().addSelfLink().addCommentsLink())  // Add the comments link
                .collect(Collectors.toList());

        CollectionModel<TaskDto> result = CollectionModel.of(taskDtos);
        result.add(linkTo(methodOn(TaskController.class).findAll()).withSelfRel());

        return result;
    }

    @GetMapping("/{id}/comments")
    public CollectionModel<CommentDto> findComments(@PathVariable Long id) {
        List<CommentDto> commentDtos = commentRepository.findByTaskId_Id(id).stream()
                .map(comment -> comment.toDto())
                .collect(toList());

        CollectionModel<CommentDto> result = CollectionModel.of(commentDtos);
        result.add(linkTo(methodOn(TaskController.class).findComments(id)).withSelfRel());

        return result;
    }
}
