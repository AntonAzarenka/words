package com.azarenka.words.controllers.user;

import com.azarenka.words.domain.Participant;
import com.azarenka.words.service.ServiceProvider;
import com.azarenka.words.service.participants.ParticipantService;
import com.azarenka.words.service.util.Windows;
import com.azarenka.words.windows.WindowsChanger;
import com.azarenka.words.windows.WindowsProvider;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class DeleteUserController {

    public ImageView backIcon;
    public ImageView deleteUserIcon;
    public ComboBox<Participant> participantsCombobox;
    @Autowired
    private ServiceProvider provider;
    @Autowired
    private WindowsChanger windowsChanger;
    @Autowired
    private WindowsProvider windowsProvider;

    public void initialize() throws IOException {
        initIcons();
        loadData();
    }

    private void loadData() {
        participantsCombobox.setItems(FXCollections.observableList(provider.getParticipantService().getParticipants()));
    }

    private void initIcons() throws IOException {
        deleteUserIcon.setImage(new Image(provider.getResourceProvider().getApplyImageResource().getURL().toExternalForm()));
        backIcon.setImage(new Image(provider.getResourceProvider().getBackImageResource().getURL().toExternalForm()));
    }

    public void back() {
        windowsChanger.closeWindow(windowsProvider.getDeleteUserWindow());
    }

    public void save() {
        ParticipantService participantService = provider.getParticipantService();
        List<Participant> participants = participantService.getParticipants();
        Participant forRemoveParticipant = participantsCombobox.getValue();
        if (Objects.nonNull(forRemoveParticipant)) {
            participants.remove(forRemoveParticipant);
            participantService.save(participants);
            provider.getRefreshService().setRefreshUserWindowProperty(
                    !provider.getRefreshService().isRefreshUserWindowProperty());
            refresh();
            back();
        } else {
            Windows.showNotificationWindow("Remove", "Unable to do it!", "Can not remove. Chose someone");
        }
    }

    public void refresh() {
        loadData();
    }
}
