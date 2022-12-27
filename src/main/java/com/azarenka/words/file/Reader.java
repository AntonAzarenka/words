package com.azarenka.words.file;

import com.azarenka.words.service.util.ApplicationUtil;

import org.slf4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents class for read file.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
public class Reader {

    private static final Logger LOGGER = ApplicationUtil.getLogger();

    public List<String> readFile(String fileName) {
        File file = new File(fileName);
        Path resourceDirectory = Paths.get(file.getAbsolutePath());
        File uploadFile = new File(String.valueOf(resourceDirectory));
        BufferedReader reader;
        List<String> stringList = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(uploadFile), StandardCharsets.UTF_8));
            while (reader.ready()) {
                stringList.add(reader.readLine());
            }
            reader.close();
        } catch (IOException e) {
            LOGGER.error("Can not read file. {}", e.getMessage());
        }
        return stringList;
    }
}
