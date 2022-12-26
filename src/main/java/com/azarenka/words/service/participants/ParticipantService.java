package com.azarenka.words.service.participants;

import com.azarenka.words.domain.Participant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class ParticipantService {

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
            System.out.println("FILE NOT FOUND");//TODO add logger
            save(Collections.emptyList());
            participants = load();
        }
        return participants;
    }

    public boolean save(List<Participant> participants) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Objects.requireNonNull(resource.getFilename()));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(participants);
        } catch (IOException e) {
            //TODO add logger
            return false;
        }
        return true;
    }
}
