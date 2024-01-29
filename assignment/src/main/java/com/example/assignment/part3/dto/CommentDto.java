package com.example.assignment.part3.dto;

import com.example.assignment.part3.entity.Tasks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CommentDto extends RepresentationModel<CommentDto> {
    private Long id;
    private Tasks taskId;
    private String author;
    private LocalDate createDate;
    private String content;

    public CommentDto addSelfLink() {
        add(Link.of(String.format("http://localhost/comment/%d", this.id)).withSelfRel());
        return this;
    }
}
