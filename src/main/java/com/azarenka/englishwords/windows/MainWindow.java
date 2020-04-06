package com.azarenka.englishwords.windows;


import com.azarenka.englishwords.CommonWindowsWidget;
import com.azarenka.englishwords.WindowLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MainWindow extends CommonWindowsWidget {

    @Value(value = "classpath:fxml/main-window.fxml")
    private Resource resource;
    private Scene main;
    private FXMLLoader mainWidgetWindow;
    private Parent mainWidgetParent;
    private WindowLoader windowLoader;

    public MainWindow(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public Scene getMain() {
        return main;
    }

    public void setMain(Scene main) {
        this.main = main;
    }

    @PostConstruct
    public void init() {
        this.windowLoader = new WindowLoader(resource);
    }

    @Override
    public void loadBean() {
        mainWidgetWindow = getWindowLoader().loadFxmlFile();
        mainWidgetWindow.setControllerFactory(aClass -> applicationContext.getBean(aClass));
        mainWidgetParent = getParent(mainWidgetWindow);
        main = new Scene(mainWidgetParent, 1280, 800);
    }

    public WindowLoader getWindowLoader() {
        return windowLoader;
    }
}
