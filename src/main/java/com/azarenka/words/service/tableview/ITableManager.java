package com.azarenka.words.service.tableview;

import java.util.HashSet;

import javafx.scene.control.TableView;

/**
 * Interface for managing of table view.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
public interface ITableManager<T> {

    /**
     * Sets items to table view.
     *
     * @param type  type of table
     * @param table table
     */
    void setItems(T type, TableView<T> table);

    /**
     * Resets table view.
     *
     * @param table table
     */
    void resetTable(TableView<T> table);

    void refresh(HashSet<T> items, TableView<T> table);
}
