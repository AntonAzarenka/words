package com.azarenka.words;

import com.azarenka.javafx.ApplicationStarter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Represents main class for staring application.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.azarenka"})
public class Application extends ApplicationStarter {

    public static void main(String[] args) {
        startApplication(args, Application.class);
    }
}
