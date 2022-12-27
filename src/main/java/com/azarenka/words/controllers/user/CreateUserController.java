package com.azarenka.words.controllers.user;

import com.azarenka.words.domain.Participant;
import com.azarenka.words.service.ServiceProvider;
import com.azarenka.words.service.participants.ParticipantService;
import com.azarenka.words.service.util.Windows;
import com.azarenka.words.windows.WindowsChanger;
import com.azarenka.words.windows.WindowsProvider;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents of create user controller.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class CreateUserController {

    @Autowired
    private ServiceProvider provider;
    @Autowired
    private WindowsChanger windowsChanger;
    @Autowired
    private WindowsProvider windowsProvider;
    @Autowired
    private DeleteUserController deleteUserController;

    public ImageView backIcon;
    public ImageView saveUserIcon;
    public TextField firstnameTextField;
    public TextField lastNameTextField;

    public void initialize() throws IOException {
        initIcons();
        loadData();
    }

    private void loadData() {
    }

    private void initIcons() throws IOException {
        saveUserIcon.setImage(
            new Image(provider.getResourceProvider().getApplyImageResource().getURL().toExternalForm()));
        backIcon.setImage(new Image(provider.getResourceProvider().getBackImageResource().getURL().toExternalForm()));
    }

    public void back() {
        windowsChanger.closeWindow(windowsProvider.getCreateUserWindow());
    }

    public void save() {
        ParticipantService participantService = provider.getParticipantService();
        if (!StringUtils.isEmpty(firstnameTextField.getText()) && !StringUtils.isEmpty(lastNameTextField.getText())) {
            Participant participant = new Participant();
            participant.setFirstName(firstnameTextField.getText());
            participant.setLastName(lastNameTextField.getText());
            List<Participant> participants = participantService.getParticipants();
            participants.add(participant);
            provider.getParticipantService().save(participants);
            provider.getRefreshService().setRefreshUserWindowProperty(
                !provider.getRefreshService().isRefreshUserWindowProperty());
            resetWindow();
            deleteUserController.refresh();
            back();
        } else {
            Windows.showErrorWindow("Create User", "Unable to create", "Fields should not be empty");
        }
    }

    private void resetWindow() {
        firstnameTextField.setText(StringUtils.EMPTY);
        lastNameTextField.setText(StringUtils.EMPTY);
    }
}
