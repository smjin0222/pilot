package com.estsoft.pilot.web.dashboard.controller;

import com.estsoft.pilot.web.dashboard.dto.BoardDto;
import com.estsoft.pilot.web.dashboard.dto.BoardInsertDto;
import com.estsoft.pilot.web.dashboard.dto.BoardSearchDto;
import com.estsoft.pilot.web.dashboard.service.BoardApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardApiController {

    private final BoardApiService boardApiService;

//    @GetMapping
//    public void getBoards(BoardSearchDto boardSearchDto, Optional<Integer> page) {
//        Pageable pageable = PageRequest.of(page.orElse(0), 20);
//        Page<BoardDto> boardDtos = boardApiService.getBoardDtoPage(boardSearchDto, pageable);
//    }

    @PostMapping
    public void registerBoard(BoardInsertDto boardInsertDto) {
        Long savedBoardId = boardApiService.insertBoard(boardInsertDto);
    }
}

