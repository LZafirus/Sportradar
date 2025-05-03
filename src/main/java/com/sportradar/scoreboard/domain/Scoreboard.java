package com.sportradar.scoreboard.domain;

import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class Scoreboard {

    private final List<Match> matches = new ArrayList<>();

    public void addMatch(Match match) {

    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {

    }

    public void removeMatch(String homeTeam, String awayTeam) {
        // will be removed?
    }

    public List<Match> summary() {
        return matches.stream()
                .sorted(Comparator.comparingInt(Match::totalScore).reversed()
                        .thenComparing(Match::getMatchStart).reversed())
                .toList();
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
