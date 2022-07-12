package com.estsoft.pilot.web.dashboard.controller;

import com.estsoft.pilot.web.dashboard.dto.*;
import com.estsoft.pilot.web.dashboard.service.BoardApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardApiController {

    private final BoardApiService boardApiService;

    @GetMapping
    public Page<BoardListDto> getBoards(BoardSearchDto boardSearchDto, Optional<Integer> page, Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(20));
        Page<BoardListDto> boardListDtoPage = boardApiService.getBoardDtoPage(boardSearchDto, pageable);

        return boardListDtoPage;
    }

    @GetMapping("/{boardId}")
    public BoardDetailDto getBoardDetail(@PathVariable Long boardId) {
        BoardDetailDto boardDetailDto = boardApiService.getBoardDetail(boardId);
        return boardDetailDto;
    }

    @PostMapping
    public void registerBoard(@Valid @RequestBody BoardInsertDto boardInsertDto) {
        Long saveId = boardApiService.registerBoard(boardInsertDto);
        log.info("saveId : {}", saveId);
    }

    @PostMapping("/replies")
    public void registerReplyBoard(@Valid @RequestBody BoardReplyDto boardReplyDto) {
        Long saveId = boardApiService.registerReplyBoard(boardReplyDto);
        log.info("saveId : {}", saveId);
    }

    @PatchMapping("/{boardId}")
    public void modifyBoard(@PathVariable Long boardId,
                            @Valid @RequestBody BoardModifyDto boardModifyDto) {
        Long updateId = boardApiService.modifyBoard(boardId, boardModifyDto);
        log.info("saveId : {}", updateId);
    }

    @DeleteMapping("/{boardId}")
    public void removeBoard(@PathVariable Long boardId) {
        boardApiService.removeBoard(boardId);
    }

    @PostMapping("/{boardId}/comments")
    public void registerComment(@PathVariable Long boardId,
                                @Valid @RequestBody CommentInsertDto commentInsertDto) {
        boardApiService.registerComment(boardId, commentInsertDto);
    }

    @PatchMapping("/{boardId}/comments/{commentId}")
    public void modifyComment(@PathVariable Long boardId,
                              @PathVariable Long commentId,
                              @Valid @RequestBody CommentModifyDto commentModifyDto) {
        boardApiService.modifyComment(boardId, commentId, commentModifyDto);
    }

    @DeleteMapping("/{boardId}/comments/{commentId}")
    public void removeComment(@PathVariable Long boardId,
                              @PathVariable Long commentId) {
        boardApiService.removeComment(boardId, commentId);
    }


}

