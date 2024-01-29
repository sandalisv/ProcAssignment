package com.example.assignment.part3.dto;

import com.example.assignment.part3.controller.TaskController;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskDto extends RepresentationModel<TaskDto> {
    private Long id;
    private String title;
    private String description;
    private String status;
    private List<CommentDto> comments;

    public TaskDto addSelfLink() {
        add(linkTo(methodOn(TaskController.class).findById(this.id)).withSelfRel());
        return this;
    }

    public TaskDto addCommentsLink() {
        add(linkTo(methodOn(TaskController.class).findComments(this.id)).withRel("comments"));
        return this;
    }

    public boolean hasTitle() {
        return title != null && !title.isEmpty();
    }

    public boolean hasDescription() {
        return description != null && !description.isEmpty();
    }

    public boolean hasStatus() {
        return status != null && !status.isEmpty();

    }

    public String getTaskStatus() {
        return status;
    }
}


