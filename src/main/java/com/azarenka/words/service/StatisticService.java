package com.azarenka.words.service;

import com.azarenka.words.domain.*;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if(contributorInformation.size() == 0){
            contributorInformation = new HashSet<>();
        }
        ParticipantInformation information = statistic.getInformation(participant);
        contributorInformation.remove(information);
        if(Objects.isNull(information)){
            information = createNewParticipantInformation(participant);
        }
        ParticipantWordStatistic participantWordStatistic = new ParticipantWordStatistic(
                LocalDate.now(), ImmutableMap.of(word,score));
        information.getWordStatistics().add(participantWordStatistic);
        Integer wordsCount = information.getWordsCount();
        information.setWordsCount(++wordsCount);
        if(!isRight){
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
