package com.azarenka.words.service.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Util class for modal windows.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
public class Windows {

    public static void showErrorWindow(String title, String header, String message) {
        show(AlertType.ERROR, title, header, message);
    }

    public static boolean showConfirmationWindow(String title, String header, String message) {
        return show(AlertType.CONFIRMATION, title, header, message);
    }

    public static void showNotificationWindow(String title, String header, String message) {
        show(AlertType.INFORMATION, title, header, message);
    }

    private static boolean show(AlertType type, String title, String header, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
        if (type == AlertType.CONFIRMATION) {
            return !alert.getResult().getText().equals("Cancel");
        }
        return true;
    }
}
