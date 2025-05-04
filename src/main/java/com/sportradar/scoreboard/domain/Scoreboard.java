package com.sportradar.scoreboard.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Scoreboard {
    private final List<Match> matches= new ArrayList<>();

    public Match startNewMatch(String homeTeam, String awayTeam) {
        validateTeamNames(homeTeam, awayTeam);
        validateMatchDoesNotExist(homeTeam, awayTeam);

        Match match = new Match(homeTeam, awayTeam);
        match.startMatch();
        matches.add(match);
        return match;
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        validateTeamNames(homeTeam, awayTeam);
        validateScores(homeScore, awayScore);
        Match match = findMatch(homeTeam, awayTeam)
                .orElseThrow(() -> new IllegalStateException("Match not found"));
        match.updateScore(homeScore, awayScore);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        validateTeamNames(homeTeam, awayTeam);
        Match match = findMatch(homeTeam, awayTeam)
                .orElseThrow(() -> new IllegalStateException("Match not found"));
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

    private Optional<Match> findMatch(String homeTeam, String awayTeam) {
        return matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findFirst();
    }

    private void validateTeamNames(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null) {
            throw new IllegalArgumentException("Team names cannot be null");
        }
        if (homeTeam.isBlank() || awayTeam.isBlank()) {
            throw new IllegalArgumentException("Team names cannot be empty");
        }
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Home team and away team cannot be the same");
        }
    }

    private void validateMatchDoesNotExist(String homeTeam, String awayTeam) {
        if (findMatch(homeTeam, awayTeam).isPresent()) {
            throw new IllegalStateException("Match between these teams already exists");
        }
    }

    private void validateScores(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
    }

}