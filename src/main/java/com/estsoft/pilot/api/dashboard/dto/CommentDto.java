package com.estsoft.pilot.api.dashboard.dto;

import com.estsoft.pilot.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class CommentDto {

    private Long commentId;

    private String content;

    private LocalDateTime createTime;

    public static List<CommentDto> of(List<Comment> comments) {
        return comments.stream()
                // .filter(c -> c.getStatus().)
                .map(comment -> CommentDto.builder()
                        .commentId(comment.getId())
                        .content(comment.getContent())
                        .createTime(comment.getCreateTime())
                        .build())
                .toList();
    }
}
