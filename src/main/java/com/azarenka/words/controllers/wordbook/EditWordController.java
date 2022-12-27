package com.azarenka.words.controllers.wordbook;

import com.azarenka.words.controllers.CommonController;
import com.azarenka.words.domain.Word;
import com.azarenka.words.file.ResourceProvider;
import com.azarenka.words.service.util.Windows;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Represents of options controller.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class EditWordController extends CommonController {

    private Word editWord;

    public TextField wordTextField;
    public TextField translateTextField;
    public ImageView backIcon;
    public ImageView saveWordIcon;

    public void back() {
        windowsChanger.closeWindow(windowsProvider.getEditWordWindow());
    }

    public void save() {
        List<Word> words = provider.getWordService().load();
        if (!StringUtils.isEmpty(wordTextField.getText()) && !StringUtils.isEmpty(translateTextField.getText())) {
            words.remove(editWord);
            words.add(new Word(wordTextField.getText(), translateTextField.getText()));
            provider.getWordService().save(words);
            provider.getRefreshService().setRefreshWordWindowProperty(
                !provider.getRefreshService().isRefreshWordWindowProperty());
            resetWindow();
            back();
        } else {
            Windows.showErrorWindow("Edit User", "Unable to edit", "Fields should not be empty");
        }
    }

    public void setWord(Word word) {
        this.editWord = word;
        wordTextField.setText(word.getWord());
        translateTextField.setText(word.getTranslate());
    }

    @Override
    protected void loadData() {
        //no need actions
    }

    @Override
    protected void initIcons() {
        ResourceProvider resourceProvider = provider.getResourceProvider();
        saveWordIcon.setImage(resourceProvider.getImage(resourceProvider::getApplyImageResource));
        backIcon.setImage(resourceProvider.getImage(resourceProvider::getBackImageResource));
    }

    @Override
    protected void loadOptions() {
        //no need actions
    }

    private void resetWindow() {
        wordTextField.setText(StringUtils.EMPTY);
        translateTextField.setText(StringUtils.EMPTY);
        this.editWord = null;
    }
}
