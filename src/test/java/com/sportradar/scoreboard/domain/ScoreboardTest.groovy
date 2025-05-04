package com.sportradar.scoreboard.domain

import spock.lang.Specification
import spock.lang.Unroll

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
            scoreboard.updateScore("Team A", "Team B", 2, 1)

        then:
            match.homeScore == 2
            match.awayScore == 1
    }

    def "FinishMatch"() {
        given:
            scoreboard.startNewMatch("Team A", "Team B")
            def match = scoreboard.summary()[0]

        when:
            scoreboard.finishMatch("Team A", "Team B")

        then:
            scoreboard.summary().isEmpty()
    }

    def "Summary"() {
        given:
        scoreboard.startNewMatch("Home1", "Away1")
        scoreboard.startNewMatch("Home2", "Away2")
        scoreboard.startNewMatch("Home3", "Away3")

            scoreboard.updateScore("Home1", "Away1", 1, 0)
            scoreboard.updateScore("Home2", "Away2", 3, 0)
            scoreboard.updateScore("Home3", "Away3", 0, 3)

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

    @Unroll
    def "should throw exception when team names are invalid"() {
        when:
        scoreboard.startNewMatch(homeTeam, awayTeam)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == expectedMessage

        where:
        homeTeam | awayTeam | expectedMessage
        null     | "Team B" | "Team names cannot be null"
        "Team A" | null     | "Team names cannot be null"
        ""       | "Team B" | "Team names cannot be empty"
        "Team A" | ""       | "Team names cannot be empty"
        "Team A" | "Team A" | "Home team and away team cannot be the same"
    }

    def "should throw exception when trying to start duplicate match"() {
        given:
        scoreboard.startNewMatch("Team A", "Team B")

        when:
        scoreboard.startNewMatch("Team A", "Team B")

        then:
        def e = thrown(IllegalStateException)
        e.message == "Match between these teams already exists"
    }

    @Unroll
    def "should throw exception when scores are invalid"() {
        given:
        scoreboard.startNewMatch("Team A", "Team B")

        when:
        scoreboard.updateScore("Team A", "Team B", homeScore, awayScore)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "Scores cannot be negative"

        where:
        homeScore | awayScore
        -1        | 0
        0        | -1
        -1        | -1
    }

    def "should throw exception when updating score of non-existent match"() {
        when:
        scoreboard.updateScore("Team A", "Team B", 1, 0)

        then:
        def e = thrown(IllegalStateException)
        e.message == "Match not found"
    }

    def "should throw exception when finishing non-existent match"() {
        when:
        scoreboard.finishMatch("Team A", "Team B")

        then:
        def e = thrown(IllegalStateException)
        e.message == "Match not found"
    }

}
