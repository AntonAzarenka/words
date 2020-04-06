package com.azarenka.englishwords;

import javafx.fxml.FXMLLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class WindowLoader {

    private Resource resource;

    public WindowLoader(Resource resource) {
        this.resource = resource;
    }

    public FXMLLoader loadFxmlFile() {
        try {
            return new FXMLLoader(resource.getURL());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
