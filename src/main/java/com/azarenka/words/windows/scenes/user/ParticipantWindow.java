package com.azarenka.words.windows.scenes.user;

import com.azarenka.javafx.load.CommonWidget;
import com.azarenka.javafx.load.FxmlFileLoader;
import com.azarenka.javafx.load.IFxmlWindow;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;

/**
 * Represents participant window class.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class ParticipantWindow extends CommonWidget implements IFxmlWindow {

    @Value("classpath:fxml/user/add-user-window.fxml")
    private Resource resource;
    private Scene scene;

    public ParticipantWindow(ApplicationContext applicationContext) {
        super(applicationContext);
        setTitle("Users");
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
