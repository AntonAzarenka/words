package com.azarenka.words.service.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Windows {

    public static void showErrorWindow(String title, String header, String message) {
        show(AlertType.ERROR, title, header, message);
    }

    public static boolean showConfirmationWindow(String title, String header, String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
        if(alert.getResult().getText().equals("Cancel")) {
            return false;
        }
        return true;
    }

    public static void showNotificationWindow(String title, String header, String message) {
        show(AlertType.INFORMATION, title, header, message);
    }

    private static void show(AlertType type, String title, String header, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
        if (type == AlertType.CONFIRMATION) {

        }
    }
}
