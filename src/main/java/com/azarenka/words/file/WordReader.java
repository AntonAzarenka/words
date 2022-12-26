package com.azarenka.words.file;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class WordReader extends Reader implements IReader<String> {

    @Override
    public List<String> getItems(File file) {
        return readFile(file.getAbsolutePath());
    }
}
