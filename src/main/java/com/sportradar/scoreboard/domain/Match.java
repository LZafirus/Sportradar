package com.sportradar.scoreboard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public abstract class Match {

    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private LocalDateTime matchStart;

    public int calculateTotalScore(int homeScore, int awayScore) {
        return homeScore + awayScore;
    }

}
