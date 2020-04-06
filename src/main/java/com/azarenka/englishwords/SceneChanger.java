package com.azarenka.englishwords;

import javafx.scene.Scene;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SceneChanger extends StageInitializer {

    public SceneChanger(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void setNewScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }
}
