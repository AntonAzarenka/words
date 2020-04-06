package com.azarenka.englishwords.util;

import org.springframework.stereotype.Component;

@Component
public class Validator {

    private int line = 0;

    public void validate(String[] str) {
        line++;
        if (str.length != 2) {
            System.out.println("Внимание. строка " + line + " :отсутствует перевод для " + str[0]);
        }
    }

    public void reset() {
        line = 0;
    }
}
