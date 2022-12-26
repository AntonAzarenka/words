package com.azarenka.words.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

public class ParticipantInformation implements Serializable {

    private Participant participant;
    private Integer wordsCount;
    private Integer wordWrongCount;
    private List<ParticipantWordStatistic> wordStatistics;
    private Integer totalScore;

    public ParticipantInformation() {
    }

    public ParticipantInformation(Participant participant, Integer wordsCount, Integer wordWrongCount, List<ParticipantWordStatistic> wordStatistics, Integer totalScore) {
        this.participant = participant;
        this.wordsCount = wordsCount;
        this.wordWrongCount = wordWrongCount;
        this.wordStatistics = wordStatistics;
        this.totalScore = totalScore;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Integer getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(Integer wordsCount) {
        this.wordsCount = wordsCount;
    }

    public Integer getWordWrongCount() {
        return wordWrongCount;
    }

    public void setWordWrongCount(Integer wordWrongCount) {
        this.wordWrongCount = wordWrongCount;
    }

    public List<ParticipantWordStatistic> getWordStatistics() {
        return wordStatistics;
    }

    public void setWordStatistics(List<ParticipantWordStatistic> wordStatistics) {
        this.wordStatistics = wordStatistics;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ParticipantInformation that = (ParticipantInformation) o;

        return new EqualsBuilder()
                .append(participant, that.participant)
                .append(wordsCount, that.wordsCount)
                .append(wordWrongCount, that.wordWrongCount)
                .append(wordStatistics, that.wordStatistics)
                .append(totalScore, that.totalScore)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(participant)
                .append(wordsCount)
                .append(wordWrongCount)
                .append(wordStatistics)
                .append(totalScore)
                .toHashCode();
    }

}
