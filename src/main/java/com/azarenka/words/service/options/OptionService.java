package com.azarenka.words.service.options;

import com.azarenka.words.domain.Language;
import com.azarenka.words.domain.Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Objects;

@Component
public class OptionService {

    @Value(value = "${app.options.file_name.url}")
    private Resource resource;
    @Value(value = "${app.options.default.random_participant}")
    private Boolean randomParticipantFlag;
    @Value(value = "${app.options.default.random_language}")
    private Boolean randomLanguageFlag;
    @Value(value = "${app.options.default.language}")
    private Language defaultLanguage;
    @Value(value = "${app.options.default.right_answer_score}")
    private Integer rightAnswerScore;
    @Value(value = "${app.options.default.wrong_answer_score}")
    private Integer wrongAnswerScore;
    @Value(value = "${app.options.default.second_attempt_score}")
    private Integer secondAttemptScore;
    @Value(value = "${app.options.default.duplicate_score}")
    private Integer duplicateScore;
    @Value(value = "${app.options.default.max_count_words}")
    private Integer maxWordsCount;

    public Options getOptions() {
        return loadOptions();
    }

    public void updateOptions(Options options) {
        if (!saveOptions(options)) {
            //TODO add logger
        }
    }

    public Options getDefaultOptions() {
        return new Options(randomParticipantFlag, randomLanguageFlag, defaultLanguage,rightAnswerScore,
                wrongAnswerScore, secondAttemptScore, duplicateScore,maxWordsCount);
    }

    private Options loadOptions() {
        Options options;
        try (FileInputStream fileInputStream = new FileInputStream(Objects.requireNonNull(resource.getFilename()));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            options = (Options) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("FILE NOT FOUND");//TODO add logger
            saveOptions(getDefaultOptions());
            options = loadOptions();
        }
        return options;
    }

    private boolean saveOptions(Options options) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Objects.requireNonNull(resource.getFilename()));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(options);
        } catch (IOException e) {
            //TODO add logger
            return false;
        }
        return true;
    }
}
