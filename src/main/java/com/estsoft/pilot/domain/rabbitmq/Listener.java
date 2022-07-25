package com.estsoft.pilot.domain.rabbitmq;

import com.estsoft.pilot.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Listener {

    private final BoardService boardService;

    @RabbitListener(queues = "BoardQueue")
    public void processMessage(@NotNull String content) {
        Long boardId = Long.parseLong(content.split(":")[1]);
        boardService.plusBoardHitsById(boardId);
    }
}