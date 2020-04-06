package com.azarenka.englishwords.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class Writer {

    @Autowired
    private ServiceProvider serviceProvider;
    @Value(value = "${spring.main.file_name}")
    private String resource;

    public void write(String en, String ru) {
        //TODO move to validator
        List<String> russianWords = serviceProvider.getWordParser().getRussianWords();
        List<String> englishWords = serviceProvider.getWordParser().getEnglishWords();
        if (validate(russianWords, englishWords)) {
            russianWords.add(ru);
            englishWords.add(en);
        }
        if (validate(russianWords, englishWords)) {
            if (russianWords.get(russianWords.size() - 1).equals(ru) && englishWords.get(englishWords.size() - 1).equals(en)) {
                List<String> list = new ArrayList<>();
                IntStream.range(0, russianWords.size()).forEach(i -> {
                    StringBuilder builder = new StringBuilder();
                    String tmp = builder
                            .append(englishWords.get(i))
                            .append(",")
                            .append(russianWords.get(i))
                            .toString();
                    list.add(tmp);
                });
                try {
                    if (list.size() > 0) {
                        write(list);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean validate(List<String> russianWords, List<String> englishWords) {
        return russianWords.size() == englishWords.size();
    }

    private void write(List<String> words) throws IOException {
        File loadFile = new File(resource);
        File lo = loadFile.getAbsoluteFile();
        BufferedWriter stream = new BufferedWriter(new PrintWriter(lo, "UTF-8"));
        words.forEach(string -> {
            try {
                stream.write(string);
                stream.flush();
                stream.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        stream.close();
    }
}
