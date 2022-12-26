package com.azarenka.words.windows.scenes.wordbook;

import com.azarenka.javafx.load.CommonWidget;
import com.azarenka.javafx.load.FxmlFileLoader;
import com.azarenka.javafx.load.IFxmlWindow;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CreateWordWindow extends CommonWidget implements IFxmlWindow {

    @Value("classpath:fxml/words/save-word-window.fxml")
    private Resource resource;
    private Scene scene;

    /**
     * Constructor.
     *
     * @param applicationContext context.
     */
    public CreateWordWindow(ApplicationContext applicationContext) {
        super(applicationContext);
        setSize(400, 240);
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
