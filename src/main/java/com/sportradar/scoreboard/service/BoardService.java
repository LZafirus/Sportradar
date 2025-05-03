package com.sportradar.scoreboard.service;

import java.util.List;

public interface BoardService {

    void startMatch();
    void updateMatch();
    void finishMatch();
    void finishedMatches(List<String> matches);
}
