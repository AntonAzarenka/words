package com.azarenka.englishwords;

import com.github.ulisesbocchio.jar.resources.JarResourceLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class MainApp extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(EnglishWordsApplication.class)
                .resourceLoader(new JarResourceLoader("classes")).run();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    @Override
    public void start(Stage primaryStage) {
        applicationContext.publishEvent(new StageEvent(primaryStage));
    }

    class StageEvent extends ApplicationEvent {

        public StageEvent(Stage primaryStage) {
            super(primaryStage);
        }

        public Stage getStage() {
            return (Stage) getSource();
        }
    }
}
