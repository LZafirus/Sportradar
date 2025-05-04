package com.sportradar.scoreboard.service;

import com.sportradar.scoreboard.domain.Match;

import java.util.List;

public interface BoardService {

    void startMatch(String homeTeam, String awayTeam);
    void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);
    List<Match> getSummary();
}
