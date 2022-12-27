package com.azarenka.words.windows;

import com.azarenka.javafx.StageInitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import javafx.stage.Stage;

/**
 * Windows initializer Sets main window for application.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
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

    public Stage getMainStage() {
        return getStage();
    }
}
