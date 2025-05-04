package com.sportradar.scoreboard.domain

import com.sportradar.scoreboard.service.ScoreboardService
import spock.lang.Specification


class ScoreboardTest extends Specification {

    Scoreboard scoreboard = Mock()
    ScoreboardService scoreboardService = new ScoreboardService(scoreboard)

    def "should start a new match"() {
        given:
        Match match = prepareMatch("Home", "Away")

        when:
        scoreboardService.startMatch(match)

        then:
        1 * scoreboard.addMatch(_ as Match) >> { Match m ->
            assert m.getHomeTeam() == "Home"
            assert m.getAwayTeam() == "Away"
        }
    }

    def "should update score for existing match"() {
        given:
        Match match = prepareMatch("Home", "Away")
        def homeScore = 2
        def awayScore = 1

        when:
        scoreboardService.updateScore(match, homeScore, awayScore)

        then:
        1 * scoreboard.updateScore(match, homeScore, awayScore)
    }

    def "should get summary of matches"() {
        given:
        Match match = prepareMatch("Home", "Away")
        def expectedMatches = [match]

        when:
        def result = scoreboardService.getSummary()

        then:
        1 * scoreboard.summary() >> expectedMatches
        result == expectedMatches
    }

    def "should return matches ordered by total score and most recent"() {
        given:
        def mexicoCanada = new Match("Mexico", "Canada")
        def spainBrazil = new Match("Spain", "Brazil")
        def germanyFrance = new Match("Germany", "France")
        def uruguayItaly = new Match("Uruguay", "Italy")
        def argentinaAustralia = new Match("Argentina", "Australia")

        // Start matches in order
        scoreboardService.startMatch(mexicoCanada)
        mexicoCanada.updateScore(0, 5)
        scoreboardService.startMatch(spainBrazil)
        spainBrazil.updateScore(10, 2)
        scoreboardService.startMatch(germanyFrance)
        germanyFrance.updateScore(2, 2)
        scoreboardService.startMatch(uruguayItaly)
        uruguayItaly.updateScore(6, 6)
        scoreboardService.startMatch(argentinaAustralia)
        argentinaAustralia.updateScore(3, 1)

        when:
        def result = scoreboardService.getSummary()

        then:
        1 * scoreboard.summary() >> [uruguayItaly, spainBrazil, mexicoCanada, argentinaAustralia, germanyFrance]

        and: "matches are ordered by total score and most recent"
        result.collect { "${it.homeTeam} ${it.homeScore} - ${it.awayTeam} ${it.awayScore}" } == [
                "Uruguay 6 - Italy 6",
                "Spain 10 - Brazil 2",
                "Mexico 0 - Canada 5",
                "Argentina 3 - Australia 1",
                "Germany 2 - France 2"
        ]
    }

    def prepareMatch(String homeTeam, String awayTeam){
        return new Match(homeTeam, awayTeam)
    }
}
