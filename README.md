# Sportradar - recruitment task

## ⚽ Scoreboard Library
#### A simple Java-based library for managing live football matches and generating real-time match summaries. 
#### Includes Groovy/Spock-based tests and follows clean, testable architecture.

---

## Features 🏈

- **Start** a new match with home and away teams, scores on 0:0, ```startTime``` on <code>LocalDateTime.now()</code>

- **Update** scores during the match - must pass actual scores (after goal with 0:0, pass 1:0)

- Generate a **summary** of ongoing matches, sorted as below

- **Sorted** by total score (home + away), descending. If scores are equal, more recently started matches appear first

- **Finish** match removes it from scoreboard list

## Technologies Used ⚾

- Java 17

- Spring Boot (minimal use)

- Spock Framework for testing (Groovy)

- Maven for build configuration
---
## Usage 🏐

Start a match:
```
scoreboard.startMatch("Mexico", "Canada");
```

Update a match:
```
scoreboard.updateScore("Mexico", "Canada", 0, 5);
```

Get match summary:
```
List<Match> summary = scoreboard.summary();
```

Finish match:
```
scoreboard.finishMatch("Mexico", "Canada");
```
---
Example Summary Output

Given the following matches:
```
Mexico 0 - Canada 5

Spain 10 - Brazil 2

Germany 2 - France 2

Uruguay 6 - Italy 6

Argentina 3 - Australia 1
```
The summary will be:
```
1. Uruguay 6 - Italy 6          (because total score is 12 and started later)

2. Spain 10 - Brazil 2          (because total score is 12 and started earlier)

3. Mexico 0 - Canada 5          (because total score is 5)

4. Argentina 3 - Australia 1    (because total score is 4 but it started later)

5. Germany 2 - France 2         (because total score is 4 but it started earlier)
```   

## Run Tests 🏀
   bash
```
   ./mvnw test
```

---

## Author's thoughts 🎳

- Application could be for sure created without Spring. I would recommend to remove it later.
- I choose Groovy because I like its flexibility
- Code has its own validation, so called from outside should check most of the cases. 
More elegant would be implementing annotations instead of methods.
- I tried to encapsulate <code>Match class</code>, but I couldn't fully remove dependency in <code>Scoreboard class</code>
- I am so sorry for messy commit messages, this is me working on my private projects, 
normally at my work I'd create branch and my Pull Request would be only one commit with ready solution
- I switched my idea from creating layers with <code>service</code> and <code>controllers</code>, 
thinking about <code>REST API</code>, wasting time on creating something fancy and big, 
to simple solution of just <code>Match class</code> keeping object and <code>Scoreboard</code> behaving more like service. 
- As simple as possible.
- Most complicated for me was to be sure my comparator/sorting works correctly, that is why there was such a mess in tests.
I was sure those are fine and code was wrong. In the end, Comparator was giving wrong order 💀
- Such a nice task, I am happy with my solution and I hope you like it. I'd likely hear feedback on me if understanding correctly a goal






