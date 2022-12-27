package com.azarenka.words.controllers;

import com.azarenka.words.service.ServiceProvider;
import com.azarenka.words.windows.WindowsChanger;
import com.azarenka.words.windows.WindowsProvider;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Represents of common controller.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
public abstract class CommonController {

    @Autowired
    protected ServiceProvider provider;
    @Autowired
    protected WindowsChanger windowsChanger;
    @Autowired
    protected WindowsProvider windowsProvider;

    public void initialize() {
        loadData();
        initIcons();
        loadOptions();
    }

    protected abstract void loadData();

    protected abstract void initIcons();

    protected abstract void loadOptions();
}
