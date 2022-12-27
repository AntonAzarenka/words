package com.azarenka.words.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

/**
 * Represents of participant word statistic domain object.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
public class ParticipantWordStatistic implements Serializable {

    private LocalDate localDate;
    private Map<Word, Integer> wordsScore;

    public ParticipantWordStatistic() {
    }

    public ParticipantWordStatistic(LocalDate localDate, Map<Word, Integer> wordsScore) {
        this.localDate = localDate;
        this.wordsScore = wordsScore;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Map<Word, Integer> getWordsScore() {
        return wordsScore;
    }

    public void setWordsScore(Map<Word, Integer> wordsScore) {
        this.wordsScore = wordsScore;
    }
}
