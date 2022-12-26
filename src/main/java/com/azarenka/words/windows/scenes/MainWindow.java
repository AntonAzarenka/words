package com.azarenka.words.windows.scenes;

import com.azarenka.javafx.load.CommonWidget;
import com.azarenka.javafx.load.FxmlFileLoader;
import com.azarenka.javafx.load.IFxmlWindow;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class MainWindow extends CommonWidget implements IFxmlWindow {

    @Value("classpath:fxml/main-window.fxml")
    private Resource resource;
    private Scene scene;

    /**
     * Constructor.
     *
     * @param applicationContext application context
     */
    public MainWindow(ApplicationContext applicationContext) {
        super(applicationContext);
        setTitle("Words");
        setSize(1280,800);
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
