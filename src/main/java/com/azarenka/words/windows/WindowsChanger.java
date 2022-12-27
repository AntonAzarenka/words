package com.azarenka.words.windows;

import com.azarenka.javafx.SceneChanger;
import com.azarenka.javafx.load.CommonWidget;
import org.springframework.stereotype.Component;

/**
 * Represents class for changing scenes.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class WindowsChanger extends SceneChanger {

    private CommonWidget activeWidget;
    private CommonWidget previousWidget;

    public void setupWindow(CommonWidget widget) {
        previousWidget = activeWidget;
        activeWidget = widget;
        setNewScene(activeWidget);
    }

    public void back() {

    }
}
