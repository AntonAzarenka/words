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
public class ChoiceWindow extends CommonWindowsWidget {

    @Value(value = "classpath:fxml/choice-window.fxml")
    private Resource resource;
    private Scene mainWindow;
    private FXMLLoader choiseWidgetWindow;
    private Parent mainWidgetParent;
    private WindowLoader windowLoader;

    public ChoiceWindow(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @PostConstruct
    public void init(){
        this.windowLoader = new WindowLoader(resource);
    }

    @Override
    public void loadBean() {
        choiseWidgetWindow = getWindowLoader().loadFxmlFile();
        choiseWidgetWindow.setControllerFactory(aClass -> applicationContext.getBean(aClass));
        mainWidgetParent = getParent(choiseWidgetWindow);
        mainWindow = new Scene(mainWidgetParent, 1280, 800);
    }

    public WindowLoader getWindowLoader() {
        return windowLoader;
    }

    public Scene getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(Scene mainWindow) {
        this.mainWindow = mainWindow;
    }
}
