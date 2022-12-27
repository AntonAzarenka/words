package com.azarenka.words.service.options;

import com.azarenka.words.domain.Language;
import com.azarenka.words.domain.Options;
import com.azarenka.words.service.util.ApplicationUtil;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 * Represents of option service class.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class OptionService {

    private static final Logger LOGGER = ApplicationUtil.getLogger();

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
            LOGGER.error("Something went wrong. Cannot save application options");
        }
    }

    public Options getDefaultOptions() {
        return new Options(randomParticipantFlag, randomLanguageFlag, defaultLanguage, rightAnswerScore,
            wrongAnswerScore, secondAttemptScore, duplicateScore, maxWordsCount);
    }

    private Options loadOptions() {
        Options options;
        try (FileInputStream fileInputStream = new FileInputStream(Objects.requireNonNull(resource.getFilename()));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            options = (Options) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.info("File 'options.data' not found. Creating new file");
            saveOptions(getDefaultOptions());
            options = loadOptions();
            LOGGER.info("File 'options.data' created successfully");
        }
        return options;
    }

    private boolean saveOptions(Options options) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Objects.requireNonNull(resource.getFilename()));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(options);
        } catch (IOException e) {
            LOGGER.error("Can not save an options file: {}", e.getMessage());
            return false;
        }
        return true;
    }
}
