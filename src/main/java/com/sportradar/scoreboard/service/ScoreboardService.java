package com.sportradar.scoreboard.service;

import com.sportradar.scoreboard.domain.Match;
import com.sportradar.scoreboard.domain.Scoreboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreboardService implements BoardService {

    @Autowired
    private Scoreboard scoreboard;

    @Override
    public void startMatch(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        match.startMatch();
        scoreboard.addMatch(match);
    }

    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        scoreboard.updateScore(homeTeam, awayTeam, homeScore, awayScore);
    }

    @Override
    public List<Match> getSummary() {
        return scoreboard.summary();
    }
}
