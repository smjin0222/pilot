package com.estsoft.pilot.api.dashboard.dto;

import com.estsoft.pilot.domain.comment.constant.CommentStatus;
import com.estsoft.pilot.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentModifyDto {

    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .status(CommentStatus.NORMAL)
                .build();
    }
}
