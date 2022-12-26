package com.azarenka.words.service.tableview;

import javafx.scene.control.TableView;

import java.util.HashSet;

public interface ITableManager<T> {

    /**
     * Sets items to table view.
     * @param type type of table
     * @param table table
     */
    void setItems(T type, TableView<T> table);

    /**
     * Resets table view.
     * @param table table
     */
    void resetTable(TableView<T> table);

    void refresh(HashSet<T> items, TableView<T> table);
}
