package com.estsoft.pilot.api.dashboard.controller;

import com.estsoft.pilot.api.dashboard.dto.*;
import com.estsoft.pilot.api.dashboard.service.DashBoardService;
import com.estsoft.pilot.domain.jwt.service.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class DashBoardController {

    private final DashBoardService dashBoardService;

    private final TokenManager tokenManager;

    @GetMapping
    public Page<BoardListDto> getBoards(BoardSearchDto boardSearchDto,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BoardListDto> boardListDtoPage = dashBoardService.getBoardDtoPage(boardSearchDto, pageable);
        return boardListDtoPage;
    }

    @GetMapping("/{boardId}")
    public BoardDetailDto getBoardDetail(@PathVariable Long boardId) {
        BoardDetailDto boardDetailDto = dashBoardService.getBoardDetail(boardId);
        return boardDetailDto;
    }

    @PostMapping
    public void registerBoard(@Valid @RequestBody BoardInsertDto boardInsertDto,
                              @RequestHeader String authorization) {
        String email = tokenManager.getMemberEmail(authorization.split(" ")[1]);
        dashBoardService.registerBoard(boardInsertDto, email);
    }

    @PostMapping("/replies")
    public void registerReplyBoard(@Valid @RequestBody BoardReplyDto boardReplyDto,
                                   @RequestHeader String authorization) {
        String email = tokenManager.getMemberEmail(authorization.split(" ")[1]);
        dashBoardService.registerReplyBoard(boardReplyDto, email);
    }

    @PatchMapping("/{boardId}")
    public void modifyBoard(@PathVariable Long boardId,
                            @Valid @RequestBody BoardModifyDto boardModifyDto,
                            @RequestHeader String authorization) {
        String email = tokenManager.getMemberEmail(authorization.split(" ")[1]);
        dashBoardService.modifyBoard(boardId, boardModifyDto, email);
    }

    @DeleteMapping("/{boardId}")
    public void removeBoard(@PathVariable Long boardId,
                            @RequestHeader String authorization) {
        String email = tokenManager.getMemberEmail(authorization.split(" ")[1]);
        dashBoardService.removeBoard(boardId, email);
    }

    @PostMapping("/{boardId}/comments")
    public void registerComment(@PathVariable Long boardId,
                                @Valid @RequestBody CommentInsertDto commentInsertDto) {
        dashBoardService.registerComment(boardId, commentInsertDto);
    }

    @PatchMapping("/{boardId}/comments/{commentId}")
    public void modifyComment(@PathVariable Long boardId,
                              @PathVariable Long commentId,
                              @Valid @RequestBody CommentModifyDto commentModifyDto) {
        dashBoardService.modifyComment(boardId, commentId, commentModifyDto);
    }

    @DeleteMapping("/{boardId}/comments/{commentId}")
    public void removeComment(@PathVariable Long boardId,
                              @PathVariable Long commentId) {
        dashBoardService.removeComment(boardId, commentId);
    }
}

