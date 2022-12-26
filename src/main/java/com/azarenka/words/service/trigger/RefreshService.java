package com.azarenka.words.service.trigger;

import javafx.beans.property.SimpleBooleanProperty;
import org.springframework.stereotype.Component;

@Component
public class RefreshService {

    private SimpleBooleanProperty refreshMainWindowProperty = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty refreshUserWindowProperty = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty refreshWordWindowProperty = new SimpleBooleanProperty(false);

    public boolean isRefreshWordWindowProperty() {
        return refreshWordWindowProperty.get();
    }

    public SimpleBooleanProperty refreshWordWindowPropertyProperty() {
        return refreshWordWindowProperty;
    }

    public void setRefreshWordWindowProperty(boolean refreshWordWindowProperty) {
        this.refreshWordWindowProperty.set(refreshWordWindowProperty);
    }

    /**
     * @return value of property.
     */
    public boolean isRefreshMainWindowProperty() {
        return refreshMainWindowProperty.get();
    }

    /**
     * @return property
     */
    public SimpleBooleanProperty refreshMainWindowPropertyProperty() {
        return refreshMainWindowProperty;
    }

    /**
     * Sets new value
     * @param refreshMainWindowProperty
     */
    public void setRefreshMainWindowProperty(boolean refreshMainWindowProperty) {
        this.refreshMainWindowProperty.set(refreshMainWindowProperty);
    }

    public boolean isRefreshUserWindowProperty() {
        return refreshUserWindowProperty.get();
    }

    public SimpleBooleanProperty refreshUserWindowPropertyProperty() {
        return refreshUserWindowProperty;
    }

    public void setRefreshUserWindowProperty(boolean refreshUserWindowProperty) {
        this.refreshUserWindowProperty.set(refreshUserWindowProperty);
    }
}
