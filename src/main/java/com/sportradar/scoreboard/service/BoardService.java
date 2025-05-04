package com.sportradar.scoreboard.service;

import com.sportradar.scoreboard.domain.Match;

import java.util.List;

public interface BoardService {

    void startMatch(Match match);
    void updateScore(Match match, int homeScore, int awayScore);
    List<Match> getSummary();
}
