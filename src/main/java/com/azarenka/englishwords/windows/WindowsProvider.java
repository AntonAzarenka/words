package com.azarenka.englishwords.windows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WindowsProvider {

    @Autowired
    private MainWindow mainWindow;
    @Autowired
    private LibraryWindow libraryWindow;
    @Autowired
    private StatisticWindow statisticWindow;
    @Autowired
    private ChoiceWindow choiceWindow;

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public LibraryWindow getLibraryWindow() {
        return libraryWindow;
    }

    public StatisticWindow getStatisticWindow() {
        return statisticWindow;
    }

    public ChoiceWindow getChoiceWindow() {
        return choiceWindow;
    }
}
