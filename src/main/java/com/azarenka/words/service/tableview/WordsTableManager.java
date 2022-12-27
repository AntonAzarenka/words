package com.azarenka.words.service.tableview;

import com.azarenka.words.domain.Word;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

/**
 * Implementation of {@link ITableManager}.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class WordsTableManager implements ITableManager<Word> {

    private Set<Word> words = new HashSet<>();

    @Override
    public void setItems(Word word, TableView<Word> table) {
        words.add(word);
        table.setItems(FXCollections.observableArrayList(words));
    }

    @Override
    public void resetTable(TableView<Word> table) {
        this.words = new HashSet<>();
        table.setItems(FXCollections.observableArrayList(words));
    }

    @Override
    public void refresh(HashSet<Word> items, TableView<Word> table) {
        resetTable(table);
        table.setItems(FXCollections.observableArrayList(items));
        table.refresh();
    }
}
