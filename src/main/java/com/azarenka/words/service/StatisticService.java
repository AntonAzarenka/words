package com.azarenka.words.service;

import com.azarenka.words.domain.Participant;
import com.azarenka.words.domain.ParticipantInformation;
import com.azarenka.words.domain.ParticipantWordStatistic;
import com.azarenka.words.domain.Statistic;
import com.azarenka.words.domain.Word;
import com.google.common.collect.ImmutableMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represent service for providing statistic.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class StatisticService {

    @Value(value = "${app.statistic.file_name.url}")
    private Resource resource;

    public Statistic load() {
        Statistic statistic;
        try (FileInputStream fileInputStream = new FileInputStream(Objects.requireNonNull(resource.getFilename()));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            statistic = (Statistic) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("FILE NOT FOUND");//TODO add logger
            updateStatistic(getDefaultStatistic());
            statistic = load();
        }
        return statistic;
    }

    private Statistic getDefaultStatistic() {
        Statistic statistic = new Statistic();
        statistic.setContributorInformation(Collections.emptySet());
        return statistic;
    }

    public void updateStatistic(Statistic statistic) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Objects.requireNonNull(resource.getFilename()));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(statistic);
        } catch (IOException e) {
            //TODO add logger
        }
    }

    public ParticipantInformation createNewParticipantInformation(Participant participant) {
        return new ParticipantInformation(participant, 0, 0, new ArrayList<>(), 0);
    }

    public void addStatistic(Word word, Integer score, Participant participant, boolean isRight) {
        Statistic statistic = load();
        Set<ParticipantInformation> contributorInformation = statistic.getContributorInformation();
        if (contributorInformation.size() == 0) {
            contributorInformation = new HashSet<>();
        }
        ParticipantInformation information = statistic.getInformation(participant);
        contributorInformation.remove(information);
        if (Objects.isNull(information)) {
            information = createNewParticipantInformation(participant);
        }
        ParticipantWordStatistic participantWordStatistic = new ParticipantWordStatistic(
            LocalDate.now(), ImmutableMap.of(word, score));
        information.getWordStatistics().add(participantWordStatistic);
        Integer wordsCount = information.getWordsCount();
        information.setWordsCount(++wordsCount);
        if (!isRight) {
            Integer wrongWordCount = information.getWordWrongCount();
            information.setWordWrongCount(++wrongWordCount);
        }
        int totalScore = information.getWordStatistics().stream()
            .flatMap(e -> e.getWordsScore().values().stream()).mapToInt(x -> x).sum();
        information.setTotalScore(totalScore);
        contributorInformation.add(information);
        statistic.setContributorInformation(contributorInformation);
        updateStatistic(statistic);
    }
}
