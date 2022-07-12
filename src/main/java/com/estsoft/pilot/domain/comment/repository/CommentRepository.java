package com.estsoft.pilot.domain.comment.repository;

import com.estsoft.pilot.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
