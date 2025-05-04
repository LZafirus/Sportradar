package com.sportradar.scoreboard.service;

import com.sportradar.scoreboard.domain.Match;
import com.sportradar.scoreboard.domain.Scoreboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class ScoreboardService implements BoardService {

    private final Scoreboard scoreboard;

    public ScoreboardService(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    @Override
    public void startMatch(Match match) {
        match.startMatch();
        scoreboard.addMatch(match);
    }

    @Override
    public void updateScore(Match match, int homeScore, int awayScore) {
        scoreboard.updateScore(match, homeScore, awayScore);
    }

    @Override
    public List<Match> getSummary() {
        return scoreboard.summary();
    }
}
