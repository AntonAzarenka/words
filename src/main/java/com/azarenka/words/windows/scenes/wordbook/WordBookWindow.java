package com.azarenka.words.windows.scenes.wordbook;

import com.azarenka.javafx.load.CommonWidget;
import com.azarenka.javafx.load.FxmlFileLoader;
import com.azarenka.javafx.load.IFxmlWindow;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;

/**
 * Represents word book window class.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class WordBookWindow extends CommonWidget implements IFxmlWindow {

    @Value("classpath:fxml/words/add-words-window.fxml")
    private Resource resource;
    private Scene scene;

    /**
     * Constructor.
     *
     * @param applicationContext context
     */
    public WordBookWindow(ApplicationContext applicationContext) {
        super(applicationContext);
        setSize(800, 500);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public void load() {
        scene = loadBean(new FxmlFileLoader(resource));
    }
}
