package com.azarenka.words.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {

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
            e.printStackTrace();//TODO add logger
        }
        return stringList;
    }
}
