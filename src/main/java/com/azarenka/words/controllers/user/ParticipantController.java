package com.azarenka.words.controllers.user;

import com.azarenka.words.domain.Participant;
import com.azarenka.words.service.ServiceProvider;
import com.azarenka.words.service.util.Windows;
import com.azarenka.words.windows.WindowsChanger;
import com.azarenka.words.windows.WindowsProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents of participant controller.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class ParticipantController {

    public ImageView addUserIcon;
    public ImageView editUserIcon;
    public ImageView removeUserIcon;
    public ImageView backIcon;
    public ListView<String> usersList;

    @Autowired
    private ServiceProvider provider;
    @Autowired
    private WindowsChanger windowsChanger;
    @Autowired
    private WindowsProvider windowsProvider;
    @Autowired
    private EditUserController editUserController;

    public void initialize() throws IOException {
        initIcons();
        loadData();
        provider.getRefreshService().refreshUserWindowPropertyProperty().addListener(
            (observableValue, aBoolean, t1) -> refresh());
    }

    public void back() {
        provider.getRefreshService().setRefreshMainWindowProperty(
            !provider.getRefreshService().isRefreshMainWindowProperty());
        windowsChanger.closeWindow(windowsProvider.getParticipantsWindow());
    }

    public void add() {
        provider.getRefreshService().setRefreshMainWindowProperty(
            !provider.getRefreshService().isRefreshMainWindowProperty());
        windowsChanger.showModalWindow(windowsProvider.getCreateUserWindow());
    }

    public void delete() {
        windowsChanger.showModalWindow(windowsProvider.getDeleteUserWindow());
    }

    private void refresh() {
        loadData();
    }

    private void loadData() {
        usersList.setItems(FXCollections.observableList(provider.getParticipantService().getParticipants().stream()
            .map(e -> String.format("%s %s", e.getFirstName(), e.getLastName())).collect(Collectors.toList())));
    }

    private void initIcons() throws IOException {
        addUserIcon.setImage(new Image(provider.getResourceProvider().getAddImageResource().getURL().toExternalForm()));
        removeUserIcon.setImage(
            new Image(provider.getResourceProvider().getRemoveImageResource().getURL().toExternalForm()));
        editUserIcon.setImage(
            new Image(provider.getResourceProvider().getEditImageResource().getURL().toExternalForm()));
        backIcon.setImage(new Image(provider.getResourceProvider().getBackImageResource().getURL().toExternalForm()));
    }

    public void leftMouse() {
        //TODO can be add menu
    }

    public void edit() {
        if (usersList.getSelectionModel().isEmpty()) {
            Windows.showNotificationWindow("Edit Event", "Unable to edit", "Can not edit. Choose someone");
        } else {
            String[] fullName = usersList.getSelectionModel().getSelectedItem().split(" ");
            Participant participant;
            if (fullName.length > 1) {
                participant = new Participant();
                participant.setFirstName(fullName[0]);
                participant.setLastName(fullName[1]);
                openEditWindow(participant);
            } else if (fullName.length > 0) {
                participant = new Participant();
                participant.setFirstName(fullName[0]);
                openEditWindow(participant);
            } else {
                Windows.showErrorWindow(
                    "UnkownError", "Some problem with the name", "You have to rich out to support");
            }
        }
    }

    private void openEditWindow(Participant participant) {
        editUserController.loadData(participant);
        windowsChanger.showModalWindow(windowsProvider.getEditUserWindow());
    }
}
