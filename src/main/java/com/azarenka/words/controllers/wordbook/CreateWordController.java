package com.azarenka.words.controllers.wordbook;

import com.azarenka.words.controllers.CommonController;
import com.azarenka.words.domain.Word;
import com.azarenka.words.file.ResourceProvider;
import com.azarenka.words.service.util.Windows;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CreateWordController extends CommonController {

    public TextField wordTextField;
    public TextField translateTextField;
    public ImageView backIcon;
    public ImageView saveWordIcon;

    public void save() {
        if(!StringUtils.isEmpty(wordTextField.getText()) && !StringUtils.isEmpty(translateTextField.getText())) {
            Word word = new Word(wordTextField.getText(), translateTextField.getText());
            List<Word> loadedWords = provider.getWordService().load();
            loadedWords.add(word);
            provider.getWordService().save(loadedWords);
            provider.getRefreshService().setRefreshWordWindowProperty(
                    !provider.getRefreshService().isRefreshWordWindowProperty());
            resetWindow();
            back();
        } else {
            Windows.showErrorWindow("Create Word", "Unable to create", "Fields should not be empty");
        }
    }

    public void back() {
        windowsChanger.closeWindow(windowsProvider.getCreateWordWindow());
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initIcons() {
        ResourceProvider resourceProvider = provider.getResourceProvider();
        saveWordIcon.setImage(resourceProvider.getImage(resourceProvider::getApplyImageResource));
        backIcon.setImage(resourceProvider.getImage(resourceProvider::getBackImageResource));
    }

    @Override
    protected void loadOptions() {

    }

    private void resetWindow() {
        wordTextField.setText(StringUtils.EMPTY);
        translateTextField.setText(StringUtils.EMPTY);
    }
}
