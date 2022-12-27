package com.azarenka.words.file;

import com.azarenka.words.service.util.ApplicationUtil;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Supplier;

import javafx.scene.image.Image;

/**
 * Represents of resource provider class.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class ResourceProvider {

    private static final Logger LOGGER = ApplicationUtil.getLogger();
    private static final String PATH = "${app.base_path}" + "${app.icons.path}";

    @Value(PATH + "${app.icon.file_name.add_user}")
    private Resource addUserImageResource;
    @Value(PATH + "${app.icon.file_name.add_word}")
    private Resource addWordImageResource;
    @Value(PATH + "${app.icon.file_name.statistic}")
    private Resource statisticImageResource;
    @Value(PATH + "${app.icon.file_name.setting}")
    private Resource settingImageResource;
    @Value(PATH + "${app.icon.file_name.back}")
    private Resource backImageResource;
    @Value(PATH + "${app.icon.file_name.apply}")
    private Resource applyImageResource;
    @Value(PATH + "${app.icon.file_name.reject}")
    private Resource rejectImageResource;
    @Value(PATH + "${app.icon.file_name.restore}")
    private Resource restoreImageResource;
    @Value(PATH + "${app.icon.file_name.add}")
    private Resource addImageResource;
    @Value(PATH + "${app.icon.file_name.remove}")
    private Resource removeImageResource;
    @Value(PATH + "${app.icon.file_name.edit}")
    private Resource editImageResource;
    @Value(PATH + "${app.icon.file_name.restart}")
    private Resource restartImageResource;
    @Value(PATH + "${app.icon.file_name.exit}")
    private Resource exitImageResource;
    @Value(PATH + "${app.icon.file_name.load_file}")
    private Resource loadFileImageResource;

    /**
     * Returns instance of {@link Image}.
     *
     * @param supplier suplier
     * @return image
     */
    public Image getImage(Supplier<Resource> supplier) {
        try {
            return new Image(supplier.get().getURL().toExternalForm());
        } catch (IOException e) {
            LOGGER.error("Can not read an image: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Resource getAddUserImageResource() {
        return addUserImageResource;
    }

    public Resource getAddWordImageResource() {
        return addWordImageResource;
    }

    public Resource getStatisticImageResource() {
        return statisticImageResource;
    }

    public Resource getSettingImageResource() {
        return settingImageResource;
    }

    public Resource getRestoreImageResource() {
        return restoreImageResource;
    }

    public Resource getBackImageResource() {
        return backImageResource;
    }

    public Resource getApplyImageResource() {
        return applyImageResource;
    }

    public Resource getRejectImageResource() {
        return rejectImageResource;
    }

    public Resource getRemoveImageResource() {
        return removeImageResource;
    }

    public Resource getAddImageResource() {
        return addImageResource;
    }

    public Resource getEditImageResource() {
        return editImageResource;
    }

    public Resource getRestartImageResource() {
        return restartImageResource;
    }

    public Resource getExitImageResource() {
        return exitImageResource;
    }

    public Resource getLoadFileImageResource() {
        return loadFileImageResource;
    }
}
