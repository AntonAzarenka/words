package com.azarenka.words.controllers;

import com.azarenka.words.domain.Language;
import com.azarenka.words.domain.Options;
import com.azarenka.words.domain.Participant;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents of main controller mediator.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
public class MainControllerMediator {

    private JFXButton wordButton;
    private JFXButton translateButton;
    private JFXButton wrongButton;
    private JFXButton secondAttempt;
    private JFXButton nextContributor;
    private JFXComboBox<Language> languageComboBox;
    private JFXComboBox<Participant> contributorComboBox;

    public MainControllerMediator(JFXButton wordButton, JFXButton translateButton, JFXButton payButton,
                                  JFXButton secondAttempt, JFXButton nextContributor,
                                  JFXComboBox<Language> languageComboBox,
                                  JFXComboBox<Participant> contributorComboBox) {
        this.wordButton = wordButton;
        this.translateButton = translateButton;
        this.wrongButton = payButton;
        this.secondAttempt = secondAttempt;
        this.nextContributor = nextContributor;
        this.languageComboBox = languageComboBox;
        this.contributorComboBox = contributorComboBox;
    }

    public void optionsChange(Options options) {
        languageComboBox.setId(options.getDefaultLanguage().name());
        languageComboBox.setDisable(options.getRandomLanguageFlag());
        contributorComboBox.setDisable(options.getRandomParticipantFlag());
        nextContributor.setDisable(!options.getRandomParticipantFlag());
    }

    public void hasContributors(boolean hasNext) {
        wordButton.setDisable(hasNext);
        translateButton.setDisable(hasNext);
        wrongButton.setDisable(hasNext);
        secondAttempt.setDisable(hasNext);
        nextContributor.setDisable(hasNext);
    }

    public void setState(MainWindowController controller) {
        wordButton.setDisable(!StringUtils.isEmpty(controller.wordField.getText())
            && StringUtils.isEmpty(controller.wordTranslate.getText()));
        translateButton.setDisable(StringUtils.isEmpty(controller.wordField.getText())
            || !StringUtils.isEmpty(controller.wordTranslate.getText()));
        wrongButton.setDisable(!StringUtils.isEmpty(controller.wordField.getText())
            && StringUtils.isEmpty(controller.wordTranslate.getText()));
        secondAttempt.setDisable(!StringUtils.isEmpty(controller.wordField.getText())
            && StringUtils.isEmpty(controller.wordTranslate.getText()));
    }

    public void setWordButton(JFXButton wordButton) {
        this.wordButton = wordButton;
    }

    public void setTranslateButton(JFXButton translateButton) {
        this.translateButton = translateButton;
    }

    public void setWrongButton(JFXButton wrongButton) {
        this.wrongButton = wrongButton;
    }

    public void setSecondAttempt(JFXButton secondAttempt) {
        this.secondAttempt = secondAttempt;
    }
}
