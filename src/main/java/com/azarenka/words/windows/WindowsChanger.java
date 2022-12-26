package com.azarenka.words.windows;

import com.azarenka.javafx.SceneChanger;
import com.azarenka.javafx.load.CommonWidget;
import org.springframework.stereotype.Component;

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
