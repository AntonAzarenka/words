package com.azarenka.englishwords.services;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AnswerMemory {
    private List<String> words = new ArrayList<>();

    public void setWord(ListView listView, String text) {
        words.add(text);
        listView.setItems(FXCollections.observableArrayList(words));
    }

    public void reset(ListView listView) {
        this.words = new ArrayList<>();
        listView.setItems(FXCollections.observableArrayList(Collections.EMPTY_LIST));
    }
}
