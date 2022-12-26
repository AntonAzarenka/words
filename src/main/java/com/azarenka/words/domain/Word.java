package com.azarenka.words.domain;

import java.io.Serializable;
import java.util.Objects;

public class Word implements Serializable {

    private String word;
    private String translate;

    public Word(String word, String translate) {
        this.word = word;
        this.translate = translate;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word) &&
                Objects.equals(translate, word1.translate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, translate);
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", translate='" + translate + '\'' +
                '}';
    }
}
