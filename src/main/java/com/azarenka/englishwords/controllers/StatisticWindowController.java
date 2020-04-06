package com.azarenka.englishwords.controllers;

import com.azarenka.englishwords.SceneChanger;
import com.azarenka.englishwords.domain.Report;
import com.azarenka.englishwords.services.ServiceProvider;
import com.azarenka.englishwords.windows.ChoiceWindow;
import com.azarenka.englishwords.windows.MainWindow;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticWindowController {
    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private MainWindow mainWindow;
    @Autowired
    private ChoiceWindow choiceWindow;
    @Autowired
    private ServiceProvider serviceProvider;
    @FXML
    public TableView<Report> tableView;
    @FXML
    public TableColumn tableColumnName;
    @FXML
    public TableColumn tableColumnWord;
    @FXML
    public TableColumn tableColumnMoney;
    @FXML
    public Label outcome;

    @SuppressWarnings("unchecked")
    public void initialize() {
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Report, String>("contributor"));
        tableColumnWord.setCellValueFactory(new PropertyValueFactory<Report, String>("words"));
        tableColumnMoney.setCellValueFactory(new PropertyValueFactory<Report, String>("outcome"));
    }

    public void back() {
        sceneChanger.setNewScene(mainWindow.getMain());
    }

    public List<Report> loadData() {
        tableView.setItems(FXCollections.observableArrayList(serviceProvider.getBookerService().getReport()));
        outcome.setText(serviceProvider.getBookerService().getOutcome());
        return serviceProvider.getBookerService().getReport();
    }

    public void choiceWords(){
        sceneChanger.setNewScene(choiceWindow.getMainWindow());
    }
}
