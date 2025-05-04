package com.sportradar.scoreboard;

import com.sportradar.scoreboard.domain.Scoreboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScoreboardConfig {

    @Bean
    public Scoreboard scoreboard() {
        return new Scoreboard();
    }

}
