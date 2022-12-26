package com.azarenka.words.windows;

import com.azarenka.words.windows.scenes.*;
import com.azarenka.words.windows.scenes.user.CreateUserWindow;
import com.azarenka.words.windows.scenes.user.DeleteUserWindow;
import com.azarenka.words.windows.scenes.user.EditUserWindow;
import com.azarenka.words.windows.scenes.user.ParticipantWindow;
import com.azarenka.words.windows.scenes.wordbook.CreateWordWindow;
import com.azarenka.words.windows.scenes.wordbook.EditWordWindow;
import com.azarenka.words.windows.scenes.wordbook.WordBookWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WindowsProvider {

    @Autowired
    private MainWindow mainWindow;
    @Autowired
    private OptionsWindow optionsWindow;
    @Autowired
    private ParticipantWindow participantsWindow;
    @Autowired
    private CreateUserWindow createUserWindow;
    @Autowired
    private DeleteUserWindow deleteUserWindow;
    @Autowired
    private EditUserWindow editUserWindow;
    @Autowired
    private WordBookWindow wordBookWindow;
    @Autowired
    private CreateWordWindow createWordWindow;
    @Autowired
    private EditWordWindow editWordWindow;

    public CreateUserWindow getCreateUserWindow() {
        return createUserWindow;
    }

    public DeleteUserWindow getDeleteUserWindow() {
        return deleteUserWindow;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public OptionsWindow getOptionsWindow() {
        return optionsWindow;
    }

    public ParticipantWindow getParticipantsWindow() {
        return participantsWindow;
    }

    public EditUserWindow getEditUserWindow() {
        return editUserWindow;
    }

    public WordBookWindow getWordBookWindow() {
        return wordBookWindow;
    }

    public CreateWordWindow getCreateWordWindow() {
        return createWordWindow;
    }

    public EditWordWindow getEditWordWindow() {
        return editWordWindow;
    }
}
