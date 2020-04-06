package com.azarenka.englishwords.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WordReader extends Reader {

    @Value(value = "${spring.main.file_name}")
    private String fileName;

    public List<String> getDocument() {
        List<String> stringList = readFile(fileName);
        return stringList;
    }
}
