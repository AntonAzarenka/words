package com.azarenka.words.file;

import java.io.File;
import java.util.List;

public interface IReader<T> {

    List<T> getItems(File file);
}
