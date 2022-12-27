package com.azarenka.words.file;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Implementation of {@link IReader}.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class WordReader extends Reader implements IReader<String> {

    /**
     * Read file
     * @param file
     * @return
     */
    @Override
    public List<String> getItems(File file) {
        return readFile(file.getAbsolutePath());
    }
}
