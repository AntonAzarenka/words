package com.azarenka.englishwords.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControllersProvider {

    @Autowired
    private ChoiceWindowController choiceWindowController;
    @Autowired
    private MainWindowController mainWindowController;
    @Autowired
    private StatisticWindowController statisticWindowController;
    @Autowired
    private LibraryWindowController libraryWindowController;

    public ChoiceWindowController getChoiceWindowController() {
        return choiceWindowController;
    }

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public StatisticWindowController getStatisticWindowController() {
        return statisticWindowController;
    }

    public LibraryWindowController getLibraryWindowController() {
        return libraryWindowController;
    }
}
