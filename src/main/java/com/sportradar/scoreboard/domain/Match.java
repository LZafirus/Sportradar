package com.sportradar.scoreboard.domain;

import java.time.LocalDateTime;
import java.util.Comparator;

class Match {
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private LocalDateTime startTime;

    Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public static final Comparator<Match> COMPARATOR = (m1, m2) -> {
        int totalScoreComparison = Integer.compare(m2.totalScore(), m1.totalScore());
        if (totalScoreComparison != 0) {
            return totalScoreComparison;
        }
        return m2.getStartTime().compareTo(m1.getStartTime());
    };

    public void startMatch() {
        this.startTime = LocalDateTime.now();
    }

    public void updateScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int totalScore() {
        return homeScore + awayScore;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}

