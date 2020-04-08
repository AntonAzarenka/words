package com.azarenka.englishwords.services;

import com.azarenka.englishwords.domain.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Randomizer {

    private final String EMPTY = "";
    @Value(value = "${spring.main.max-amount}")
    private int maxAmount;
    @Autowired
    private WordParser parser;
    private int wordNumber;

    public String getWord(Language language) {
        List<String> words = getWords(language);
        int rnd = (int) (Math.random() * words.size() - 1);
        wordNumber = rnd;
        return words.get(rnd);
    }

    public String getTranslate(Language language) {
        if (validateWordNumber()) {
            List<String> words = getWordsToTranslate(language);
            return words.get(wordNumber);
        }
        return EMPTY;
    }

    public void reset() {
        wordNumber = -1;
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
}
