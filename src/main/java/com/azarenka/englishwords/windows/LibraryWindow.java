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
public class LibraryWindow extends CommonWindowsWidget {

    @Value(value = "classpath:fxml/words-window.fxml")
    private Resource resource;
    private Scene main;
    private FXMLLoader libraryWidgetWindow;
    private Parent mainWidgetParent;
    private WindowLoader windowLoader;

    public LibraryWindow(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @PostConstruct
    public void init() {
        this.windowLoader = new WindowLoader(resource);
    }

    @Override
    public void loadBean() {
        libraryWidgetWindow = getWindowLoader().loadFxmlFile();
        libraryWidgetWindow.setControllerFactory(aClass -> applicationContext.getBean(aClass));
        mainWidgetParent = getParent(libraryWidgetWindow);
        main = new Scene(mainWidgetParent, 1280, 800);
    }

    public Scene getMain() {
        return main;
    }

    public WindowLoader getWindowLoader() {
        return windowLoader;
    }
}
