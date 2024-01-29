package com.example.assignment.part3.entity;

import com.example.assignment.part3.dto.TaskDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tasks {

    @Id
    private Long id;
    private String title;
    private String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @OneToMany(mappedBy = "taskId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comments> comments = new ArrayList<>();

    public Tasks(TaskBuilder builder) {
        id = builder.id;
        description = builder.description;
        status = builder.status;
    }

    public TaskDto toDto() {
        TaskDto dto = TaskDto.builder()
                .id(id)
                .title(title)
                .description(description)
                .status(status != null ? status.name() : null)
                .comments(comments.stream().map(Comments::toDto).collect(Collectors.toList()))
                .build();
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void update(TaskDto dto) {
        if (dto.hasTitle()) {
            title = dto.getTitle();
        }

        if (dto.hasDescription()) {
            description = dto.getDescription();
        }

        if (dto.hasStatus()) {
            status = TaskStatus.valueOf(dto.getTaskStatus());
        }
    }

    public boolean hasComments() {
        return !comments.isEmpty();
    }

    public static class TaskBuilder {
        private Long id;
        private String title;
        private String description;
        private TaskStatus status;

        public TaskBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TaskBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public Tasks build() {
            return new Tasks(this);
        }
    }
}
