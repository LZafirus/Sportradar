package com.sportradar.scoreboard.domain

import spock.lang.Specification

class ScoreboardTest extends Specification {

    Scoreboard scoreboard = new Scoreboard()

    def "StartNewMatch"() {
        when:
            scoreboard.startNewMatch("Team A", "Team B")

        then:
            def matches = scoreboard.summary()
            matches.size() == 1
            matches[0].homeTeam == "Team A"
            matches[0].awayTeam == "Team B"
            matches[0].homeScore == 0
            matches[0].awayScore == 0
    }

    def "UpdateScore"() {
        given:
            scoreboard.startNewMatch("Team A", "Team B")
            def match = scoreboard.summary()[0]

        when:
            scoreboard.updateScore(match, 2, 1)

        then:
            match.homeScore == 2
            match.awayScore == 1
    }

    def "FinishMatch"() {
        given:
            scoreboard.startNewMatch("Team A", "Team B")
            def match = scoreboard.summary()[0]

        when:
            scoreboard.finishMatch(match)

        then:
            scoreboard.summary().isEmpty()
    }

    def "Summary"() {
        given:
        scoreboard.startNewMatch("Home1", "Away1")
        scoreboard.startNewMatch("Home2", "Away2")
        scoreboard.startNewMatch("Home3", "Away3")

            scoreboard.updateScore(match1, 1, 0)
            scoreboard.updateScore(match2, 3, 0)
            scoreboard.updateScore(match3, 0, 3)

        when:
            def summary = scoreboard.summary()

        then:
            summary.size() == 3
    }

    def "should handle multiple matches"() {
        when:
            scoreboard.startNewMatch("Team A", "Team B")
            scoreboard.startNewMatch("Team C", "Team D")
            scoreboard.startNewMatch("Team E", "Team F")

        then:
            scoreboard.summary().size() == 3
    }

    def prepareMatch(String home, String away) {
        return Match.create(home, away)
    }
}
