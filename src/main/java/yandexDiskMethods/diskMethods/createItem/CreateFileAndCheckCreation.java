package yandexDiskMethods.diskMethods.createItem;

import io.qameta.allure.Step;
import org.apache.commons.httpclient.HttpStatus;
import org.testng.Assert;

public class CreateFileAndCheckCreation {
    @Step("Create file and check creation")
    public static int createFileAndCheckCreation(String filePath, String destinationPath) {
        //code 200 for link, 201 for creation, 200 for validation
        Assert.assertEquals(CreateFile.createFile(filePath, destinationPath), HttpStatus.SC_CREATED);
        return EnsureItemCreatedOrRestored.ensureItemCreatedOrRestored(filePath);
    }
}

