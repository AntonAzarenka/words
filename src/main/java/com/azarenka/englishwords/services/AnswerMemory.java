package com.azarenka.englishwords.services;

import com.azarenka.englishwords.domain.Word;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AnswerMemory {
    private Set<Word> words = new HashSet<>();

    public void setWord(String word, String translate, TableView<Word> tableView) {
        words.add(new Word(word, translate));
        tableView.setItems(FXCollections.observableArrayList(words));
    }

    public void reset(TableView<Word> tableView) {
        this.words = new HashSet<>();
        tableView.setItems(FXCollections.observableArrayList(words));
    }
}
