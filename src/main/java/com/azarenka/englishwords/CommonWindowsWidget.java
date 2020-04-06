package com.azarenka.englishwords;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public abstract class CommonWindowsWidget {

    protected ApplicationContext applicationContext;

    public CommonWindowsWidget(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public abstract void loadBean();

    protected Parent getParent(FXMLLoader loader) {
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }
}
