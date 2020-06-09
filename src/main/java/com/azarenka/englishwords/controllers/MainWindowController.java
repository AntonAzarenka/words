package com.azarenka.englishwords.controllers;

import com.azarenka.englishwords.SceneChanger;
import com.azarenka.englishwords.windows.ChoiceWindow;
import com.azarenka.englishwords.windows.LibraryWindow;
import com.azarenka.englishwords.windows.StatisticWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MainWindowController {

    @Autowired
    private LibraryWindow libraryWindow;
    @Autowired
    private SceneChanger sceneChanger;
    @Autowired
    private ChoiceWindow choiceWindow;
    @Autowired
    private StatisticWindow statisticWindow;
    @Autowired
    private ControllersProvider controllersProvider;

  /*  @PostConstruct
    private void init() {
        choiceWindow.loadBean();
        statisticWindow.loadBean();
        libraryWindow.loadBean();
    }*/

    public void addWords() {
        sceneChanger.setNewScene(libraryWindow.getMain());
        controllersProvider.getLibraryWindowController().loadData();
    }

    public void statistic() {
        sceneChanger.setNewScene(statisticWindow.getMain());
        controllersProvider.getStatisticWindowController().loadData();
    }

    public void start() {
        sceneChanger.setNewScene(choiceWindow.getMainWindow());
    }
}
