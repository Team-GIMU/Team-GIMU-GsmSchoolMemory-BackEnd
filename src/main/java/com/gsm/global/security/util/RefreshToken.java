package com.gsm.global.security.util;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "refreshToken", timeToLive = 60L * 60 * 24 * 7)
@Builder
@Getter
public class RefreshToken {

    @Id
    @Indexed
    private String email;

    @Indexed
    private String token;
}
