package com.questy.helpers;

import com.questy.enums.AnswerVisibilityEnum;

import java.util.*;

public class UserScores {

    private Map<Integer, UserScore> userScoresMap = new HashMap<Integer, UserScore>();

    public void isSetCriteria(Integer userId, Integer optionScore, AnswerVisibilityEnum visibility, Date createdOn, String flare) {

        // Retrieve or create user score
        UserScore userScore = getOrCreateUserScore(userId);

        // Document criteria
        userScore.incrementScore(optionScore);

        // Document that user score matches a new set criteria
        userScore.incrementOptionsMet();

        // Update to the lowest visibility of user score
        userScore.analyzeVisibility(visibility);

        // Update to the latest date
        userScore.analyzeLatestDate(createdOn);

        // Add flare to user score
        userScore.appendFlare(flare);
    }


    /**
     * Retrieves creates a new user score
     */
    private UserScore getOrCreateUserScore(Integer userId) {

        UserScore out = userScoresMap.get(userId);

        if (out == null) {
            out = new UserScore();
            out.setUserId(userId);
            userScoresMap.put(userId, out);
        }

        return out;
    }

    /**
     * Returns all user scores that meet the query
     */
    public List<UserScore> getQueryMetUserScores() {

        List<UserScore> out = new ArrayList<UserScore>();
        for (UserScore userScore : userScoresMap.values()) {

            if (userScore.isQueryMet())
                out.add(userScore);

        }

        return out;
    }



    public Collection<UserScore> getUserScores() {
        return userScoresMap.values();
    }

    public String toString() {

        StringBuilder buf = new StringBuilder();
        for (UserScore score : userScoresMap.values()) {
            buf.append(score.toString());
            buf.append('\n');
        }

        return buf.toString();
    }
}
