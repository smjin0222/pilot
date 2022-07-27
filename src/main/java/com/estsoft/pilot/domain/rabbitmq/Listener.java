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

    @RabbitListener(queues = "BHUQueue")
    public void processMessage(@NotNull String content) {
        boardService.plusBoardHitsById(Long.parseLong(content));
    }
}