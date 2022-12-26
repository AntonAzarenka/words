package com.azarenka.words;

import com.azarenka.javafx.ApplicationStarter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.azarenka"})
public class Application extends ApplicationStarter {

    public static void main(String[] args) {
        startApplication(args, Application.class);
    }
}
