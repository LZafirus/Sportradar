package com.sportradar.scoreboard.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Scoreboard {
    private final List<Match> matches= new ArrayList<>();

    public Match startNewMatch(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        match.startMatch();
        matches.add(match);
        return match;
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        findMatch(homeTeam, awayTeam).ifPresent(match -> match.updateScore(homeScore, awayScore));
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        findMatch(homeTeam, awayTeam).ifPresent(matches::remove);
    }

    public List<Match> summary() {
        return matches.stream()
                .sorted(Comparator
                        .comparingInt(Match::totalScore).reversed()
                        .thenComparing(Match::getStartTime).reversed()
                )
                .collect(Collectors.toList());
    }

    private Optional<Match> findMatch(String homeTeam, String awayTeam) {
        return matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findFirst();
    }

}