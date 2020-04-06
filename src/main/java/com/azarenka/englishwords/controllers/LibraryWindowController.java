package com.azarenka.englishwords.controllers;

import com.azarenka.englishwords.SceneChanger;
import com.azarenka.englishwords.domain.Words;
import com.azarenka.englishwords.services.ServiceProvider;
import com.azarenka.englishwords.windows.WindowsProvider;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryWindowController {

    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private WindowsProvider windowsProvider;
    @Autowired
    private ControllersProvider controllersProvider;
    @Autowired
    private ServiceProvider serviceProvider;
    @FXML
    public TableView<Words> tableView;
    @FXML
    public TableColumn tableColumnEn;
    @FXML
    public TableColumn tableColumnRu;
    @FXML
    public TextField textFieldEn;
    @FXML
    public TextField textFieldRu;
    @FXML
    public TextField textFieldSearch;

    @SuppressWarnings("unchecked")
    public void initialize() {
        tableColumnEn.setCellValueFactory(new PropertyValueFactory<Words, String>("en"));
        tableColumnRu.setCellValueFactory(new PropertyValueFactory<Words, String>("ru"));
    }

    public void reset() {
        tableView.setItems(null);
        loadData();
    }

    public void search() {
        tableView.setItems(FXCollections.observableArrayList(serviceProvider.getSearcher().search(textFieldSearch.getText())));
    }

    public void choice() {
        sceneChanger.setNewScene(windowsProvider.getChoiceWindow().getMainWindow());
    }

    public void statistic() {
        sceneChanger.setNewScene(windowsProvider.getStatisticWindow().getMain());
        controllersProvider.getStatisticWindowController().loadData();
    }

    public void addWords() {
        if(!textFieldEn.getText().isEmpty()) {
            serviceProvider.getWriter().write(textFieldEn.getText(), textFieldRu.getText());
            textFieldEn.setText(StringUtils.EMPTY);
            textFieldRu.setText(StringUtils.EMPTY);
            serviceProvider.getWordParser().update();
            loadData();
        }
    }

    public void back() {
        sceneChanger.setNewScene(windowsProvider.getMainWindow().getMain());
    }

    public void loadData() {
        tableView.setItems(FXCollections.observableArrayList(serviceProvider.getWordParser().getAllWords()));
    }
}
