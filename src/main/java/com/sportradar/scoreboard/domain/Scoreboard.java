package com.sportradar.scoreboard.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Scoreboard {
    private final List<Match> matches = new ArrayList<>();

    public void startNewMatch(String homeTeam, String awayTeam) {
        Match match = Match.create(homeTeam, awayTeam);
        addMatch(match);
    }

    public void updateScore(Match match, int homeScore, int awayScore) {
        match.updateScore(homeScore, awayScore);
    }

    public void finishMatch(Match match) {
        matches.remove(match);
    }

    public List<Match> summary() {
        return matches.stream()
                .sorted(Comparator
                        .comparingInt(Match::totalScore).reversed()
                        .thenComparing(Match::getStartTime).reversed()
                )
                .collect(Collectors.toList());
    }

    private void addMatch(Match match) {
        matches.add(match);
    }
}