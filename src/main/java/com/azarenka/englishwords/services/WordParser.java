package com.azarenka.englishwords.services;

import com.azarenka.englishwords.domain.Words;
import com.azarenka.englishwords.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class WordParser {

    @Autowired
    private WordReader wordReader;
    @Autowired
    private Validator validator;

    private Map<String, String> stringMap = new HashMap<>();

    public List<String> getRussianWords() {
        init();
        List<String> russianWords = new ArrayList<>();
        stringMap.forEach((eWord, rWord) -> {
            russianWords.add(rWord);
        });
        return russianWords;
    }

    public List<String> getEnglishWords() {
        init();
        List<String> englishWords = new ArrayList<>();
        stringMap.forEach((eWord, rWord) -> {
            englishWords.add(eWord);
        });
        return englishWords;
    }

    private void init() {
        if (stringMap.isEmpty()) {
            parseWords();
        }
    }

    private void parseWords() {
        List<String> document = wordReader.getDocument();
        document.forEach(string -> {
            String[] str = string.split(",");
            validator.validate(str);
            if (str.length > 1) {
                stringMap.put(str[0], str[1]);
            } else {
                stringMap.put(str[0], "");
            }
        });
        validator.reset();
    }

    public void update(){
        parseWords();
    }

    public List<Words> getAllWords() {
        List<Words> words = new ArrayList<>();
        List<String> russianWords = getRussianWords();
        List<String> englishWords = getEnglishWords();
        IntStream.range(0, englishWords.size()).forEach(i -> {
            Words word = new Words();
            word.setEn(englishWords.get(i));
            word.setRu(russianWords.get(i));
            words.add(word);
        });
        return words;
    }
}
