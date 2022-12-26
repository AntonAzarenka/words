package com.azarenka.words.controllers.options;

import com.azarenka.words.domain.Language;
import com.azarenka.words.domain.Options;
import com.azarenka.words.service.ServiceProvider;
import com.azarenka.words.service.util.Windows;
import com.azarenka.words.windows.WindowsChanger;
import com.azarenka.words.windows.WindowsProvider;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
public class OptionsController {

    @Autowired
    private ServiceProvider provider;
    @Autowired
    private WindowsChanger windowsChanger;
    @Autowired
    private WindowsProvider windowsProvider;

    public ImageView backIcon;
    public ImageView saveIcon;
    public ImageView rejectIcon;
    public ImageView restoreIcon;
    public JFXCheckBox randomParticipantsCheckbox;
    public JFXCheckBox randomLangCheckbox;
    public JFXComboBox<Language> defaultLanguageCombobox;
    public TextField wrongAnswerTextField;
    public TextField rightAnswerTexField;
    public TextField maxCountWords;
    public TextField secondAttemptTextField;
    public TextField wrongAnswerDuplicateTextField;

    public void initialize() throws IOException {
        initIcons();
        loadData();
    }

    public void back() {
        provider.getRefreshService().setRefreshMainWindowProperty(
                !provider.getRefreshService().isRefreshMainWindowProperty());
        windowsChanger.closeWindow(windowsProvider.getOptionsWindow());
    }

    public void saveOptions() {
        Options options = buildOptions();
        if (Objects.nonNull(options)) {
            provider.getOptionService().updateOptions(buildOptions());
            provider.getRefreshService().setRefreshMainWindowProperty(
                    !provider.getRefreshService().isRefreshMainWindowProperty());
            Windows.showNotificationWindow("Save Options", "Game will restart", "After saving the game will reloaded");
            back();
        }
    }

    public void reject() {
        loadData();
    }

    public void restoreOptions() {
        setData(provider.getOptionService().getDefaultOptions());
    }

    private Options buildOptions() {
        Options options = null;
        if (validate()) {
            options = new Options(
                    randomParticipantsCheckbox.isSelected(),
                    randomLangCheckbox.isSelected(),
                    defaultLanguageCombobox.getValue(),
                    Integer.parseInt(rightAnswerTexField.getText()),
                    Integer.parseInt(wrongAnswerTextField.getText()),
                    Integer.parseInt(secondAttemptTextField.getText()),
                    Integer.parseInt(wrongAnswerDuplicateTextField.getText()),
                    Integer.parseInt(maxCountWords.getText())
            );
        }
        return options;
    }

    private boolean validate() {
        try {
            Integer.parseInt(rightAnswerTexField.getText());
            Integer.parseInt(wrongAnswerTextField.getText());
            Integer.parseInt(secondAttemptTextField.getText());
            Integer.parseInt(wrongAnswerDuplicateTextField.getText());
            Integer.parseInt(maxCountWords.getText());
        } catch (NumberFormatException exception) {
            Windows.showErrorWindow("Wrong data", "Incorrect data", "Should be only digits");
            return false;
        }
        return true;
    }

    private void setData(Options options) {
        defaultLanguageCombobox.setItems(FXCollections.observableList(Arrays.asList(Language.values())));
        randomParticipantsCheckbox.setSelected(options.getRandomParticipantFlag());
        randomLangCheckbox.setSelected(options.getRandomLanguageFlag());
        defaultLanguageCombobox.setValue(options.getDefaultLanguage());
        wrongAnswerTextField.setText(String.valueOf(options.getWrongAnswerScore()));
        rightAnswerTexField.setText(String.valueOf(options.getRightAnswerScore()));
        maxCountWords.setText(String.valueOf(options.getMaxWordsCount()));
        secondAttemptTextField.setText(String.valueOf(options.getSecondAttemptScore()));
        wrongAnswerDuplicateTextField.setText(String.valueOf(options.getDuplicateScore()));
    }

    private void loadData() {
        setData(provider.getOptionService().getOptions());
    }

    private void initIcons() throws IOException {
        backIcon.setImage(new Image(provider.getResourceProvider().getBackImageResource().getURL().toExternalForm()));
        saveIcon.setImage(new Image(provider.getResourceProvider().getApplyImageResource().getURL().toExternalForm()));
        rejectIcon.setImage(new Image(provider.getResourceProvider().getRejectImageResource().getURL().toExternalForm()));
        restoreIcon.setImage(new Image(provider.getResourceProvider().getRestoreImageResource().getURL().toExternalForm()));
    }
}
