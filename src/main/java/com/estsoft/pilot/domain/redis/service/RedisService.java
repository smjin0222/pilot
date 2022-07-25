package com.estsoft.pilot.domain.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisService {

    private final RedisTemplate redisTemplate;

    public void plusBoardCount() {
        ValueOperations<String, Object> vop = redisTemplate.opsForValue();
        Object cnt = vop.get("board::count");
        Long result = Long.parseLong(String.valueOf(cnt)) + 1L;
        vop.set("board::count", result);
    }
}
