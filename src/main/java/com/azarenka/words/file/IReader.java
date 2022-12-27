package com.azarenka.words.file;

import java.io.File;
import java.util.List;

/**
 * Interface for convert to object from file.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
public interface IReader<T> {

    List<T> getItems(File file);
}
