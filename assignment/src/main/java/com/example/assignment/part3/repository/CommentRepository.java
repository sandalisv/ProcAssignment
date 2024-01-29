package com.example.assignment.part3.repository;

import com.example.assignment.part3.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByTaskId_Id(Long taskId);
}
