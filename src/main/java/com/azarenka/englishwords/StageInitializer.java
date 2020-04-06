package com.azarenka.englishwords;

import com.azarenka.englishwords.MainApp.StageEvent;
import com.azarenka.englishwords.windows.MainWindow;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public abstract class StageInitializer implements ApplicationListener<StageEvent> {

    @Autowired
    protected MainWindow commonWindowsWidget;
    private ApplicationContext applicationContext;
    static Stage stage;

    public StageInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageEvent stageEvent) {
        stage = stageEvent.getStage();

        commonWindowsWidget.loadBean();
        stage.setScene(commonWindowsWidget.getMain());
        stage.setTitle("Distribution words");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Sets new scene.
     *
     * @param scene scene
     */
    public abstract void setNewScene(Scene scene);
}
