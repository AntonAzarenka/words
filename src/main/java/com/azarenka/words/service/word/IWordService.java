package com.azarenka.words.service.word;

import com.azarenka.words.domain.Language;
import com.azarenka.words.domain.Word;

import java.util.List;

import javafx.scene.Scene;

/**
 * Interface for managing of words.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
public interface IWordService {

    /**
     * Gets word in String format.
     *
     * @param language instance of {@link Language}
     * @return word
     */
    String getWord(Language language);

    /**
     * Gets translate of word.
     *
     * @return translate
     */
    String getTranslate();

    /**
     * Checks that word already was today or not.
     *
     * @return <code>true</code> if word appears, otherwise <code>false</code>
     */
    boolean hasWordToday();

    /**
     * Resets words for run.
     */
    void reset();

    /**
     * Saves list of words.
     *
     * @param words list of words
     * @return count of words
     */
    int save(List<Word> words);

    /**
     * Loads words.
     *
     * @return list of {@link Word}
     */
    List<Word> load();

    /**
     * Upload words from file.
     *
     * @param scene current scene to be able to choose the file.
     * @return count words.
     */
    int uploadFile(Scene scene);

    /**
     *
     */
    void reloadWords();
}
