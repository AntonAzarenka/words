package com.azarenka.englishwords.controllers;

import com.azarenka.englishwords.SceneChanger;
import com.azarenka.englishwords.domain.Contributor;
import com.azarenka.englishwords.domain.Language;
import com.azarenka.englishwords.services.ServiceProvider;
import com.azarenka.englishwords.windows.MainWindow;
import com.azarenka.englishwords.windows.StatisticWindow;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ChoiceWindowController {

    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private MainWindow choiceWindow;
    @Autowired
    private StatisticWindow statisticWindow;
    @Autowired
    private ServiceProvider provider;
    @Autowired
    private StatisticWindowController windowController;
    @Value(value = "classpath:money.jpg")
    Resource resource;
    @FXML
    public TextField wordField;
    @FXML
    public ListView listView;
    @FXML
    public ComboBox<Language> languageComboBox;
    @FXML
    public ComboBox<Contributor> contributorComboBox;
    @FXML
    public Label wordTranslate;
    @FXML
    public Label showMoney;
    @FXML
    public Label timer;
    @FXML
    public ImageView imageView;
    @FXML
    public Button rightButton;
    private Task task;

    public void initialize() {
        loadData();
        languageComboBox.setValue(Language.EN);
        contributorComboBox.setOnAction(event -> reset());
    }

    public void back() {
        sceneChanger.setNewScene(choiceWindow.getMain());
    }

    public void getWord() {
        wordField.setText(provider.getRandomizer().getWord(languageComboBox.getValue()));
        showMoney.setText(StringUtils.EMPTY);
        wordTranslate.setText(StringUtils.EMPTY);
        imageView.setImage(null);
        answer();
    }

    public void comboAction() {
        languageComboBox.valueProperty().addListener(
                (ObservableValue<? extends Language> observable, Language oldValue, Language newValue) -> {});
    }

    public void rightAnswer() {
        if (!wordField.getText().isEmpty()) {
            task.cancel();
            provider.getAnswerMemory().setWord(listView, wordField.getText());
            wordTranslate.setText(StringUtils.EMPTY);
        }
    }

    public void reset() {
        provider.getAnswerMemory().reset(listView);
        provider.getRandomizer().reset();
        wordField.setText(StringUtils.EMPTY);
        wordTranslate.setText(StringUtils.EMPTY);
        showMoney.setText(StringUtils.EMPTY);
        imageView.setImage(null);
    }

    public void translate() {
        if (!wordField.getText().isEmpty()) {
            String translate = provider.getRandomizer().getTranslate(languageComboBox.getValue());
            wordTranslate.setText(translate);
        }
    }

    public void payMoney() {
        String word = wordField.getText();
        if (!word.isEmpty()) {
            task.cancel();
            int money = provider.getRandomizer().getAmount();
            loadImage();
            showMoney.setText(String.valueOf(money));
            provider.getAnswerMemory().setWord(listView, word);
            provider.getBookerService().setReceivable(contributorComboBox.getValue(), wordField.getText(), money);
        }
    }

    public void addMoneyForWrongPronunciation(){
        showMoney.setText(String.valueOf(10));
        provider.getBookerService().setReceivable(contributorComboBox.getValue(), "pronunciation", 10);
    }

    public void statistic() {
        sceneChanger.setNewScene(statisticWindow.getMain());
        windowController.loadData();
    }

    public void loadImage() {
        Image image = new Image("money.jpg");
        imageView.setImage(image);
    }

    public void loadData() {
        languageComboBox.setItems(FXCollections.observableArrayList(Language.EN, Language.RU));
        contributorComboBox.setItems(FXCollections.observableArrayList(provider.getContributorParser().getContributors()));
        contributorComboBox.setValue(provider.getContributorParser().getContributors().get(0));
    }

    private void answer() {
        task = new Task<AtomicBoolean>() {
            @Override
            public AtomicBoolean call() throws InterruptedException {
                for (int i = 15; i >= 0; i--) {
                    if (isCancelled()) {
                        break;
                    }
                    this.updateMessage(i + "");
                    Thread.sleep(1000);
                }
                return null;
            }
        };
        timer.textProperty().bind(task.messageProperty());
        new Thread(task).start();
    }
}
