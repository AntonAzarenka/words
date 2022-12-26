package com.azarenka.words.domain;

import java.io.Serializable;

public class Options implements Serializable {

    private Boolean randomParticipantFlag;
    private Boolean randomLanguageFlag;
    private Language defaultLanguage;
    private Integer rightAnswerScore;
    private Integer wrongAnswerScore;
    private Integer secondAttemptScore;
    private Integer duplicateScore;
    private Integer maxWordsCount;

    public Options() {
    }

    public Options(Boolean randomParticipantFlag, Boolean randomLanguageFlag, Language defaultLanguage,
                   Integer rightAnswerScore, Integer wrongAnswerScore, Integer secondAttemptScore,
                   Integer duplicateScore, Integer maxWordsCount) {
        this.randomParticipantFlag = randomParticipantFlag;
        this.randomLanguageFlag = randomLanguageFlag;
        this.defaultLanguage = defaultLanguage;
        this.rightAnswerScore = rightAnswerScore;
        this.wrongAnswerScore = wrongAnswerScore;
        this.secondAttemptScore = secondAttemptScore;
        this.duplicateScore = duplicateScore;
        this.maxWordsCount = maxWordsCount;
    }

    public Boolean getRandomParticipantFlag() {
        return randomParticipantFlag;
    }

    public void setRandomParticipantFlag(Boolean randomParticipantFlag) {
        this.randomParticipantFlag = randomParticipantFlag;
    }

    public Boolean getRandomLanguageFlag() {
        return randomLanguageFlag;
    }

    public void setRandomLanguageFlag(Boolean randomLanguageFlag) {
        this.randomLanguageFlag = randomLanguageFlag;
    }

    public Language getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(Language defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public Integer getRightAnswerScore() {
        return rightAnswerScore;
    }

    public void setRightAnswerScore(Integer rightAnswerScore) {
        this.rightAnswerScore = rightAnswerScore;
    }

    public Integer getWrongAnswerScore() {
        return wrongAnswerScore;
    }

    public void setWrongAnswerScore(Integer wrongAnswerScore) {
        this.wrongAnswerScore = wrongAnswerScore;
    }

    public Integer getSecondAttemptScore() {
        return secondAttemptScore;
    }

    public void setSecondAttemptScore(Integer secondAttemptScore) {
        this.secondAttemptScore = secondAttemptScore;
    }

    public Integer getDuplicateScore() {
        return duplicateScore;
    }

    public void setDuplicateScore(Integer duplicateScore) {
        this.duplicateScore = duplicateScore;
    }

    public Integer getMaxWordsCount() {
        return maxWordsCount;
    }

    public void setMaxWordsCount(Integer maxWordsCount) {
        this.maxWordsCount = maxWordsCount;
    }
}
