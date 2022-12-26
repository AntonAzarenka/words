package com.azarenka.words.file;

import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Supplier;

@Component
public class ResourceProvider {

    private static final String PATH = "com/azarenka/words/icons/";
    private static final String BASE_PATH = "classpath:";

    @Value(BASE_PATH + PATH + "${app.icon.file_name.add_user}")
    private Resource addUserImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.add_word}")
    private Resource addWordImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.statistic}")
    private Resource statisticImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.setting}")
    private Resource settingImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.back}")
    private Resource backImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.apply}")
    private Resource applyImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.reject}")
    private Resource rejectImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.restore}")
    private Resource restoreImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.add}")
    private Resource addImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.remove}")
    private Resource removeImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.edit}")
    private Resource editImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.restart}")
    private Resource restartImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.exit}")
    private Resource exitImageResource;
    @Value(BASE_PATH + PATH + "${app.icon.file_name.load_file}")
    private Resource loadFileImageResource;

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

    public Image getImage(Supplier<Resource> supplier){
        try {
            return new Image(supplier.get().getURL().toExternalForm());
        } catch (IOException e) {
            throw new RuntimeException(e);
            //TODO add logger
        }
    }
}
