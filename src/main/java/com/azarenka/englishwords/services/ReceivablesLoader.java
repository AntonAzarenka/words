package com.azarenka.englishwords.services;

import com.azarenka.englishwords.domain.Receivables;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Component
public class ReceivablesLoader {

    @Value(value = "classpath:receivables.res")
    private Resource resource;

    public Set<Receivables> load() {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        Set<Receivables> receivables = new HashSet<>();
        try {
            fileInputStream = new FileInputStream(resource.getFilename());
            objectInputStream = new ObjectInputStream(fileInputStream);
            receivables = (Set<Receivables>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return receivables;
    }

    public boolean upload(Set<Receivables> receivablesList) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(resource.getFilename());
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(receivablesList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
