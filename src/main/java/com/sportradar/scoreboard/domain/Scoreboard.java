package com.sportradar.scoreboard.domain;

import java.util.*;
import java.util.stream.Collectors;


public class Scoreboard {

    private final List<Match> matches = new ArrayList<>();

    public void addMatch(Match match) {
        if (findMatch(match.getHomeTeam(), match.getAwayTeam()).isPresent()) {
            throw new IllegalArgumentException("Match already exists");
        }
        matches.add(match);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = getMatch(homeTeam, awayTeam);
        match.updateScore(homeScore, awayScore);
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
                .filter(match -> match.getHomeTeam().equalsIgnoreCase(homeTeam)
                        && match.getAwayTeam().equalsIgnoreCase(awayTeam))
                .findAny();
    }

    private Match getMatch(String homeTeam, String awayTeam) {
        return findMatch(homeTeam, awayTeam)
                .orElseThrow(() -> new NoSuchElementException("Match not found"));
    }

    /*
    custom exception to do
     */


}
