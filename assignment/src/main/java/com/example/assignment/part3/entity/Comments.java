package com.example.assignment.part3.entity;


import com.example.assignment.part3.dto.CommentDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Comments {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tasks_id")
    @JsonBackReference
    private Tasks taskId;
    private String author;
    private LocalDate createDate;
    private String content;
    public CommentDto toDto() {
        CommentDto dto = CommentDto.builder()
                .id(id)
                .taskId(taskId)
                .author(author)
                .createDate(createDate)
                .content(content)
                .build();
        return dto;
    }


    public Long getId() {
        return id;
    }
}
