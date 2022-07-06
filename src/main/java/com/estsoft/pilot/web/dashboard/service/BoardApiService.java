package com.estsoft.pilot.web.dashboard.service;

import com.estsoft.pilot.web.dashboard.dto.BoardDto;
import com.estsoft.pilot.web.dashboard.dto.BoardInsertDto;
import com.estsoft.pilot.web.dashboard.dto.BoardSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardApiService {
    public Page<BoardDto> getBoardDtoPage(BoardSearchDto boardSearchDto, Pageable pageable) {
        return null;
    }

    @Transactional
    public Long insertBoard(BoardInsertDto boardInsertDto) {
        return null;
    }
}
