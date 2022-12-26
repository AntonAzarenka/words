package com.azarenka.words.windows;

import com.azarenka.javafx.StageInitializer;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class WindowInitializer extends StageInitializer {

    @Autowired
    private WindowsProvider windowsProvider;
    @Value(value = "${app.properties_url}")
    private Resource resource;

    /**
     * Sets main window for start application
     */
    @PostConstruct
    public void init() {
        setCommonWidget(windowsProvider.getMainWindow());
        setupPropertiesUrl(resource);
    }

    public Stage getMainStage(){
        return getStage();
    }
}
