package com.azarenka.words.controllers.wordbook;

import com.azarenka.words.controllers.CommonController;
import com.azarenka.words.domain.Word;
import com.azarenka.words.file.ResourceProvider;
import com.azarenka.words.service.util.Windows;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * Represents of work book controller.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class WorkBookController extends CommonController {

    @Autowired
    private EditWordController editWordController;

    public ImageView loadWordsIcon;
    public ImageView addWordIcon;
    public ImageView removeWordIcon;
    public ImageView backIcon;
    public ImageView editWordIcon;
    public TableColumn<Word, String> wordTableColumn;
    public TableColumn<Word, String> translateTableColumn;
    public TableView<Word> tableWords;

    public void add() {
        windowsChanger.showModalWindow(windowsProvider.getCreateWordWindow());
    }

    public void delete() {
        Word selectedItem = tableWords.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(selectedItem)) {
            boolean answer = Windows.showConfirmationWindow("Delete Window",
                String.format("Are you really want to delete '%s - %s' word", selectedItem.getWord(),
                    selectedItem.getTranslate()), StringUtils.EMPTY);
            if (answer) {
                List<Word> words = provider.getWordService().load();
                words.remove(selectedItem);
                provider.getWordService().save(words);
                refresh();
            }
        } else {
            Windows.showErrorWindow("Delete Window", "Unable to delete", "Choose the word to delete.");
        }
    }

    public void back() {
        windowsChanger.closeWindow(windowsProvider.getWordBookWindow());
    }

    public void edit() {
        Word selectedItem = tableWords.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(selectedItem)) {
            editWordController.setWord(selectedItem);
            windowsChanger.showModalWindow(windowsProvider.getEditWordWindow());
        } else {
            Windows.showNotificationWindow("Edit Word", "Unable to edit", "Can not edit. Choose the word to edit");
        }
    }

    public void load() {
        int countWords = provider.getWordService().uploadFile(windowsProvider.getWordBookWindow().getScene());
        if (countWords > -1) {
            Windows.showNotificationWindow("Information", "File was uploaded",
                String.format("%s word was added", countWords));
            provider.getRefreshService().setRefreshWordWindowProperty(
                !provider.getRefreshService().isRefreshWordWindowProperty());
        }
    }

    @Override
    protected void loadData() {
        provider.getRefreshService().refreshWordWindowPropertyProperty().addListener(
            (observableValue, aBoolean, t1) -> refresh());
        wordTableColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        translateTableColumn.setCellValueFactory(new PropertyValueFactory<>("translate"));
        provider.getWordsTableManager().refresh(new HashSet<>(provider.getWordService().load()), tableWords);
       /* provider.getWordService().load().forEach(word ->
                provider.getWordsTableManager().setItems(new Word(word.getWord(), word.getTranslate()), tableWords));*/
    }

    @Override
    protected void initIcons() {
        ResourceProvider resourceProvider = provider.getResourceProvider();
        loadWordsIcon.setImage(resourceProvider.getImage(resourceProvider::getLoadFileImageResource));
        addWordIcon.setImage(resourceProvider.getImage(resourceProvider::getAddImageResource));
        editWordIcon.setImage(resourceProvider.getImage(resourceProvider::getEditImageResource));
        removeWordIcon.setImage(resourceProvider.getImage(resourceProvider::getRemoveImageResource));
        backIcon.setImage(resourceProvider.getImage(resourceProvider::getBackImageResource));
    }

    @Override
    protected void loadOptions() {
        //no actions needed
    }

    private void refresh() {
        provider.getWordsTableManager().refresh(new HashSet<>(provider.getWordService().load()), tableWords);
    }
}
