package com.azarenka.words.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents of util methods.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
public class ApplicationUtil {

    /**
     * Returns logger.
     *
     * @return instance of {@link Logger}
     */
    public static Logger getLogger() {
        final Throwable t = new Throwable();
        t.fillInStackTrace();
        final String clazz = t.getStackTrace()[1].getClassName();
        return LoggerFactory.getLogger(clazz);
    }
}
