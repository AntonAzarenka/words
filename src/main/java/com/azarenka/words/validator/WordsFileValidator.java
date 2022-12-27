package com.azarenka.words.validator;

import org.springframework.stereotype.Component;

/**
 * Util class for validation file of words.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class WordsFileValidator {

    private int line = 0;

    public void validate(String[] str) {
        line++;
        if (str.length < 2) {
            System.out.println("Attention. Line is  " + line + " :translate is empty " + str[0]);
        }
    }

    public void reset() {
        line = 0;
    }
}
