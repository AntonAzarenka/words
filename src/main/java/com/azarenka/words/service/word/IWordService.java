package com.azarenka.words.service.word;

import com.azarenka.words.domain.Language;
import com.azarenka.words.domain.Word;
import javafx.scene.Scene;

import java.util.List;

public interface IWordService {

    String getWord(Language language);

    String getTranslate();

    boolean hasWordToday();

    void reset();

    int save(List<Word> words);

    List<Word> load();

    int uploadFile(Scene scene);

    void reloadWords();
}
