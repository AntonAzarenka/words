package com.azarenka.englishwords.controllers;

import com.azarenka.englishwords.SceneChanger;
import com.azarenka.englishwords.domain.Contributor;
import com.azarenka.englishwords.domain.Language;
import com.azarenka.englishwords.domain.Report;
import com.azarenka.englishwords.domain.Word;
import com.azarenka.englishwords.services.ServiceProvider;
import com.azarenka.englishwords.windows.LibraryWindow;
import com.azarenka.englishwords.windows.StatisticWindow;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ChoiceWindowController {

    @Autowired
    private ServiceProvider provider;
    @Autowired
    private StatisticWindowController windowController;
    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private LibraryWindow libraryWindow;
    @Autowired
    private StatisticWindow statisticWindow;
    @FXML
    public JFXComboBox<Language> languageComboBox;
    @FXML
    public JFXComboBox<Contributor> contributorComboBox;
    @FXML
    public Label wordField;
    @FXML
    public Label wordTranslate;
    @FXML
    public Label timer;
    @FXML
    public Label showMoney;
    @FXML
    public TableView<Word> tableView;
    @FXML
    public TableColumn tableColumnWord;
    @FXML
    public TableColumn tableColumnTranslate;
    private Task task;

    @PostConstruct
    private void init() {
        statisticWindow.loadBean();
        libraryWindow.loadBean();
    }

    public void initialize() {
        loadData();
        languageComboBox.setValue(Language.ALL);
        contributorComboBox.setOnAction(event -> reset());
        tableColumnWord.setCellValueFactory(new PropertyValueFactory<Word, String>("word"));
        tableColumnTranslate.setCellValueFactory(new PropertyValueFactory<Report, String>("translate"));
    }

    public void loadData() {
        languageComboBox.setItems(FXCollections.observableArrayList(Language.values()));
        contributorComboBox.setItems(FXCollections.observableArrayList(provider.getContributorParser().getContributors()));
        contributorComboBox.setValue(provider.getContributorParser().getContributors().get(0));
    }

    public void rightAnswer() {
        if (!wordField.getText().isEmpty()) {
            task.cancel();
            provider.getAnswerMemory().setWord(wordField.getText(),
                    provider.getRandomizer().getTranslate(languageComboBox.getValue()), tableView);
            wordTranslate.setText(StringUtils.EMPTY);
        }
    }

    public void payMoney() {
        String word = wordField.getText();
        if (!word.isEmpty()) {
            task.cancel();
            int money = provider.getRandomizer().getAmount();
            showMoney.setText(String.valueOf(money));
            provider.getAnswerMemory().setWord(wordField.getText(),
                    provider.getRandomizer().getTranslate(languageComboBox.getValue()), tableView);
            provider.getBookerService().setReceivable(contributorComboBox.getValue(), wordField.getText(), money);
        }
    }

    public void getWord() {
        wordField.setText(provider.getRandomizer().getWord(languageComboBox.getValue()));
        showMoney.setText(StringUtils.EMPTY);
        wordTranslate.setText(StringUtils.EMPTY);
        startTimer();
    }

    public void translate() {
        if (!wordField.getText().isEmpty()) {
            String translate = provider.getRandomizer().getTranslate(languageComboBox.getValue());
            wordTranslate.setText(translate);
        }
    }

    public void reset() {
        if (null != task) {
            task.cancel();
        }
         provider.getAnswerMemory().reset(tableView);
        provider.getRandomizer().reset();
        wordField.setText(StringUtils.EMPTY);
        wordTranslate.setText(StringUtils.EMPTY);
        showMoney.setText(StringUtils.EMPTY);
    }

    public void secondAttempt() {
        showMoney.setText(String.valueOf(100));
        provider.getAnswerMemory().setWord(wordField.getText(),
                provider.getRandomizer().getTranslate(languageComboBox.getValue()), tableView);
        provider.getBookerService().setReceivable(contributorComboBox.getValue(), "second attempt", 100);
    }

    public void badWords() {
        int money = provider.getRandomizer().getAmount();
        showMoney.setText(String.valueOf(money));
        provider.getAnswerMemory().setWord(wordField.getText(),
                provider.getRandomizer().getTranslate(languageComboBox.getValue()), tableView);
        provider.getBookerService().setReceivable(contributorComboBox.getValue(), "fuck", money);
    }

    public void statistic() {
        sceneChanger.setNewScene(statisticWindow.getMain());
        windowController.loadData();
    }

    public void addMoneyForWrongPronunciation() {
        showMoney.setText(String.valueOf(10));
        provider.getBookerService().setReceivable(contributorComboBox.getValue(), "pronunciation", 10);
    }

    private void startTimer() {
        task = new Task<AtomicBoolean>() {
            @Override
            public AtomicBoolean call() throws InterruptedException {
                timer.setTextFill(Paint.valueOf("BLACK"));
                for (int i = 15; i >= 0; i--) {
                    if (isCancelled()) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    this.updateMessage(i + "");
                    if (i == 5) {
                        timer.setTextFill(Paint.valueOf("RED"));
                    }
                    Thread.sleep(1000);
                }
                return null;
            }
        };
        timer.textProperty().bind(task.messageProperty());
        new Thread(task).start();
    }
}
