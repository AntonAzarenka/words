package com.azarenka.words.controllers;

import com.azarenka.words.domain.Language;
import com.azarenka.words.domain.Options;
import com.azarenka.words.domain.Participant;
import com.azarenka.words.domain.Word;
import com.azarenka.words.file.ResourceProvider;
import com.azarenka.words.service.participants.ParticipantParser;
import com.azarenka.words.service.util.Windows;
import com.azarenka.words.service.word.IWordService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

/**
 * Represents of main window controller.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Controller
public class MainWindowController extends CommonController {

    private MainControllerMediator mediator;
    private int standardTime = 15;
    private final int repeatableTime = 10;
    private Options options;
    private Task<?> task;

    public JFXComboBox<Language> languageComboBox;
    public JFXComboBox<Participant> contributorComboBox;
    public Label wordField;
    public Label wordTranslate;
    public Label timer;
    public Label showMoney;
    public TableView<Word> tableView;
    public TableColumn<Word, String> tableColumnWord;
    public TableColumn<Word, String> tableColumnTranslate;
    public ImageView addWordIcon;
    public ImageView addUser;
    public ImageView statisticIcon;
    public ImageView settingIcon;
    public ImageView exitIcon;
    public JFXButton wordButton;
    public JFXButton translateButton;
    public JFXButton wrongButton;
    public JFXButton secondAttempt;
    public JFXButton nextContributor;
    public ImageView restartIcon;


    protected void loadOptions() {
        options = provider.getOptionService().getOptions();
        boolean isRandom = provider.getOptionService().getOptions().getRandomLanguageFlag();
        languageComboBox.setValue(isRandom ? getRandomLanguage() : options.getDefaultLanguage());
        contributorComboBox.setValue(getParticipant(options));
        mediator.optionsChange(options);
        mediator.hasContributors(!options.getRandomParticipantFlag()
            || provider.getParticipantParser().getContributors().size() == 0);
        mediator.setState(this);
    }

    public void loadData() {
        languageComboBox.setItems(FXCollections.observableArrayList(Language.values()));
        List<Participant> contributors = provider.getParticipantParser().getContributors();
        contributors.sort(Comparator.comparing(Participant::getFirstName));
        contributorComboBox.setItems(FXCollections.observableArrayList(contributors));
        tableColumnWord.setCellValueFactory(new PropertyValueFactory<>("word"));
        tableColumnTranslate.setCellValueFactory(new PropertyValueFactory<>("translate"));
        mediator = new MainControllerMediator(wordButton, translateButton, wrongButton, secondAttempt, nextContributor,
            languageComboBox, contributorComboBox);
        provider.getRefreshService().refreshMainWindowPropertyProperty().addListener(
            (observableValue, aBoolean, t1) -> refresh());
    }

    public void refresh() {
        reset();
        loadOptions();
        loadData();
    }

    @SuppressWarnings("unchecked")
    public void reset() {
        if (null != task) {
            standardTime = 15;
            task.cancel();
        }
        wordField.setText(StringUtils.EMPTY);
        wordTranslate.setText(StringUtils.EMPTY);
        provider.getParticipantParser().reset();
        provider.getWordService().reset();
        provider.getWordsTableManager().resetTable(tableView);
    }

    public void getWord() {
        wordField.setText(provider.getWordService().getWord(languageComboBox.getValue()));
        showMoney.setText(StringUtils.EMPTY);
        wordTranslate.setText(StringUtils.EMPTY);
        startTimer(provider.getWordService().hasWordToday() ? repeatableTime : standardTime);
        mediator.setState(this);
    }

    private void startTimer(int time) {
        task = new Timer(time);
        timer.textProperty().bind(task.messageProperty());
        new Thread(task).start();
    }

    public void translate() {
        if (!wordField.getText().isEmpty()) {
            String translate = provider.getWordService().getTranslate();
            wordTranslate.setText(translate);
            task.cancel();
            if (!provider.getWordService().hasWordToday()) {
                provider.getWordsTableManager()
                    .setItems(new Word(wordField.getText(), wordTranslate.getText()), tableView);
            }
            mediator.setState(this);
        }
    }

    public void nextContributor() {
        Participant nextContributor = provider.getParticipantParser().getNextContributor();
        if (Objects.isNull(nextContributor)) {
            mediator.hasContributors(true);
            Windows.showNotificationWindow("Info message", "Баста карапузики", "Доступные жертвы занчились");
        }
        contributorComboBox.setValue(nextContributor);
        provider.getWordsTableManager().resetTable(tableView);
        wordField.setText(StringUtils.EMPTY);
        wordTranslate.setText(StringUtils.EMPTY);
    }

    public void wrong() {
        IWordService wordService = provider.getWordService();
        Word word = new Word(wordField.getText(), wordTranslate.getText());
        Integer score = wordService.hasWordToday() ? options.getDuplicateScore() : options.getWrongAnswerScore();
        Participant participant = contributorComboBox.getValue();
        provider.getStatisticService().addStatistic(word, score, participant, false);
    }

    public void openSetting() {
        windowsChanger.showModalWindow(windowsProvider.getOptionsWindow());
    }

    public void restart() {
        if (Windows.showConfirmationWindow("Restart Window", "Would you like to restart?", "Restart game.")) {
            refresh();
        }
    }

    public void openParticipantsWindow() {
        windowsChanger.showModalWindow(windowsProvider.getParticipantsWindow());
    }

    protected void initIcons() {
        ResourceProvider resourceProvider = provider.getResourceProvider();
        addUser.setImage(resourceProvider.getImage(resourceProvider::getAddUserImageResource));
        addWordIcon.setImage(resourceProvider.getImage(resourceProvider::getAddWordImageResource));
        statisticIcon.setImage(resourceProvider.getImage(resourceProvider::getStatisticImageResource));
        settingIcon.setImage(resourceProvider.getImage(resourceProvider::getSettingImageResource));
        restartIcon.setImage(resourceProvider.getImage(resourceProvider::getRestartImageResource));
        exitIcon.setImage(resourceProvider.getImage(resourceProvider::getExitImageResource));
    }

    private Language getRandomLanguage() {
        return ((int) (Math.random() * 100)) % 2 == 0 ? Language.EN : Language.RU;
    }

    //TODO move to ParticipantService
    private Participant getParticipant(Options options) {
        Participant participant = null;
        ParticipantParser contributorParser = provider.getParticipantParser();
        if (options.getRandomParticipantFlag()) {
            participant = contributorParser.getNextContributor();
        } else {
            List<Participant> contributors = contributorParser.getContributors();
            if (!CollectionUtils.isEmpty(contributors)) {
                participant = contributors.get(0);
            }
        }
        return participant;
    }

    public void exit() {
        System.exit(0);
    }

    public void addWord() {
        windowsChanger.showModalWindow(windowsProvider.getWordBookWindow());
    }

    private class Timer extends Task<AtomicBoolean> {

        private int time = 0;

        public Timer(int time) {
            this.time = time;
        }

        @Override
        protected AtomicBoolean call() throws Exception {
            timer.setTextFill(Paint.valueOf("BLACK"));
            for (int i = time; i >= 0; i--) {
                if (isCancelled()) {
                    Thread.currentThread().interrupt();
                    break;
                }
                this.updateMessage(String.valueOf(i));
                if (i == 5) {
                    timer.setTextFill(Paint.valueOf("RED"));
                }
                Thread.sleep(1000);
            }
            wrong();
            return null;
        }
    }
}
