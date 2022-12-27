package com.azarenka.words.service.participants;

import com.azarenka.words.domain.Participant;
import com.azarenka.words.service.util.ApplicationUtil;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents of participant service class.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class ParticipantService {

    private static final Logger LOGGER = ApplicationUtil.getLogger();

    @Value(value = "${app.contributors.filename}")
    private Resource resource;

    public List<Participant> getParticipants() {
        return load();
    }

    private List<Participant> load() {
        List<Participant> participants;
        try (FileInputStream fileInputStream = new FileInputStream(Objects.requireNonNull(resource.getFilename()));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            participants = new ArrayList<>((List<Participant>) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.info("File 'participants.data' not found. Creating new file");
            save(Collections.emptyList());
            participants = load();
            LOGGER.info("File 'participants.data' created successfully");
        }
        return participants;
    }

    public boolean save(List<Participant> participants) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Objects.requireNonNull(resource.getFilename()));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(participants);
        } catch (IOException e) {
            LOGGER.error("Can not save an participants file: {}", e.getMessage());
            return false;
        }
        return true;
    }
}
