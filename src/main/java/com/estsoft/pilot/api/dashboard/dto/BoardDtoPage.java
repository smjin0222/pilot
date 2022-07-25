package com.estsoft.pilot.api.dashboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardDtoPage {

    private List<BoardDto> content;

    private int size;

    private int totalPages;

    private Long totalElements;

    @Builder
    public BoardDtoPage(List<BoardDto> content, int size, Long totalElements) {
        this.content = content;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = size == 0 ? 1 : (int) Math.ceil((double) totalElements / (double) size);
    }
}
