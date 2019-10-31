package yandexDiskMethods.trashMethods.removeFromTrash;

import io.qameta.allure.Step;
import org.apache.commons.httpclient.HttpStatus;
import org.testng.Assert;
import yandexDiskMethods.diskMethods.createItem.EnsureItemCreatedOrRestored;

public class RestoreItemFromTrashAndEnsureMovedToDisk {
    @Step("Restore file or folder from trash and ensure item moved to disk")
    public static int restoreItemFromTrashAndEnsureMovedToDisk(String path, String name) {
        //code 201 for restore, 200 - for validation
        Assert.assertEquals(RestoreItemFromTrash.restoreItemFromTrash(name), HttpStatus.SC_CREATED);
        return EnsureItemCreatedOrRestored.ensureItemCreatedOrRestored(path);
    }
}
