package yandexDiskMethods.diskMethods.createItem;

import io.qameta.allure.Step;
import org.apache.commons.httpclient.HttpStatus;
import org.testng.Assert;

public class CreateFolderAndCheckCreation {
    @Step("Create folder and check creation")
    public static int createFolderAndCheckCreation(String folderPath) {
        //code 201 for creation, 200 for validation
        Assert.assertEquals(CreateFolder.createFolder(folderPath), HttpStatus.SC_CREATED);
        return EnsureItemCreatedOrRestored.ensureItemCreatedOrRestored(folderPath);
    }
}
