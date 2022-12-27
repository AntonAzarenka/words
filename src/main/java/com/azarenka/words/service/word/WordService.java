package com.azarenka.words.service.word;

import com.azarenka.words.domain.Language;
import com.azarenka.words.domain.Word;
import com.azarenka.words.file.WordReader;
import com.azarenka.words.validator.WordsFileValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javafx.scene.Scene;
import javafx.stage.FileChooser;

/**
 * Implementation of {@link IWordService}.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class WordService implements IWordService {

    private static final String COMMA_DELIMITER = ",";

    @Value(value = "${app.main.max-amount}")
    private int maxAmount;
    @Value(value = "${app.words.filename}")
    private Resource resource;
    @Autowired
    private WordReader wordReader;
    @Autowired
    private WordsFileValidator validator;

    private final List<Word> todayWords = new ArrayList<>();
    private final List<Word> words = new ArrayList<>();
    private Word currentWord;
    private Language currentLanguage;

    @Override
    public String getWord(Language language) {
        currentLanguage = getCurrentLanguage(language);
        currentWord = getRndWord();
        todayWords.add(currentWord);
        return currentLanguage == Language.EN ? currentWord.getWord() : currentWord.getTranslate();
    }

    private Language getCurrentLanguage(Language language) {
        return Language.ALL == language
            ? (int) (Math.random() * 100) % 2 == 0 ? Language.EN : Language.RU
            : language;
    }

    @Override
    public String getTranslate() {
        return Language.EN == currentLanguage
            ? currentWord.getTranslate()
            : currentWord.getWord();
    }

    @Override
    public boolean hasWordToday() {
        return todayWords.stream().filter(e -> e.equals(currentWord)).count() > 1;
    }

    @Override
    public void reset() {
        todayWords.clear();
    }

    @Override
    public List<Word> load() {
        List<Word> words;
        try (FileInputStream fileInputStream = new FileInputStream(Objects.requireNonNull(resource.getFilename()));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            words = new ArrayList<>((List<Word>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("FILE NOT FOUND");//TODO add logger
            save(Collections.emptyList());
            words = load();
        }
        return words;
    }

    /**
     * Returns count of loaded words. If file wasn't loaded return -1
     *
     * @param scene
     * @return
     */
    @Override
    public int uploadFile(Scene scene) {
        int countWords = -1;
        File file = chooseFile(scene);
        if (Objects.nonNull(file)) {
            List<Word> words = load();
            List<Word> loadedWords = readFile(file);
            words.addAll(loadedWords);
            save(words);
            reloadWords();
            countWords = loadedWords.size();
        }
        return countWords;
    }

    @Override
    public void reloadWords() {
        words.clear();
        words.addAll(load());
    }

    private File chooseFile(Scene scene) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload file");
        return fileChooser.showOpenDialog(scene.getWindow());
    }

    @Override
    public int save(List<Word> words) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Objects.requireNonNull(resource.getFilename()));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(words);
        } catch (IOException e) {
            //TODO add logger
            return 0;
        }
        return words.size();
    }

    private Word getRndWord() {
        if (words.isEmpty()) {
            words.addAll(load());
        }
        Collections.shuffle(words);
        return words.get((int) (Math.random() * words.size() - 1));
    }

    public List<Word> readFile(File file) {
        List<String> document = wordReader.getItems(file);
        List<Word> allWords = new ArrayList<>();
        document.forEach(string -> {
            String[] str = string.split(COMMA_DELIMITER);
            validator.validate(str);
            allWords.add(new Word(str[0], str.length > 1 ? str[1] : "Перевод отстутсвует. Отредактируйте слово"));
        });
        validator.reset();
        return allWords;
    }
}
