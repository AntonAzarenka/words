package com.azarenka.englishwords.services;

import com.azarenka.englishwords.domain.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Randomizer {

    private final String EMPTY = "";
    @Value(value = "${spring.main.max-amount}")
    private int maxAmount;
    @Autowired
    private WordParser parser;
    private int wordNumber;
    private Language currentLanguage;
    private List<String> englishWords = new ArrayList<>();
    private List<String> russianWords = new ArrayList<>();

    public String getWord(Language language) {
        init();
        List<String> words;
        if (Language.ALL != language) {
            words = getWords(language);
        } else {
            words = getAllWords();
        }
        int rnd = (int) (Math.random() * words.size() - 1);
        wordNumber = rnd;
        return words.get(rnd);
    }

    public String getTranslate(Language language) {
        List<String> words;
        if (Language.ALL != language) {
            if (validateWordNumber()) {
                words = getWordsToTranslate(language);
                return words.get(wordNumber);
            }
        } else {
            if (currentLanguage.equals(Language.EN)) {
                words = getWordsToTranslate(Language.EN);
                return words.get(wordNumber);
            } else {
                words = getWordsToTranslate(Language.RU);
                return words.get(wordNumber);
            }
        }
        return EMPTY;
    }

    public void reset() {
        wordNumber = -1;
    }

    private List<String> getAllWords() {
        currentLanguage = rndLang();
        return getWords(currentLanguage);
    }

    private Language rndLang() {
        return getAmount() % 2 == 0
                ? Language.EN
                : Language.RU;
    }

    private List<String> getWords(Language language) {
        return language == Language.EN
                ? parser.getEnglishWords()
                : parser.getRussianWords();
    }

    private List<String> getWordsToTranslate(Language language) {
        return language == Language.EN
                ? parser.getRussianWords()
                : parser.getEnglishWords();
    }

    private boolean validateWordNumber() {
        return wordNumber >= 0;
    }

    public int getAmount() {
        int rnd = (int) (Math.random() * maxAmount);
        return rnd > 10 ? rnd : 50;
    }

    private void init() {
        if (englishWords.size() < 1) {
            englishWords.addAll(parser.getEnglishWords());
        }
        if (russianWords.size() < 1) {
            russianWords.addAll(parser.getRussianWords());
        }
    }
}

