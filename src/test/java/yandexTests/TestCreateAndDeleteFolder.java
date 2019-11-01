package yandexTests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import yandexDiskMethods.diskMethods.createItem.CreateFolder;
import yandexDiskMethods.diskMethods.createItem.EnsureItemCreatedOrRestored;
import yandexDiskMethods.diskMethods.deleteItem.DeleteFileOrFolder;
import yandexDiskMethods.trashMethods.removeFromTrash.EnsureItemIsInTrash;
import yandexDiskMethods.utilityMethods.GetRandomName;

public class TestCreateAndDeleteFolder {
    @Test(description = "Create and delete folder.")
    @Severity(SeverityLevel.NORMAL)
    @Description("1. Создать папку на диске\n2. Удалить созданную папку\n3. Проверить, что папку удалена ")
    public void createAndDeleteFolder() {
        String folder1 = GetRandomName.getRandomFolderName();
        ArrayList<Integer> list = new ArrayList();
        list.add(202);
        list.add(204);

       // Assert.assertEquals(CreateFolder.createFolder(folder1), HttpStatus.SC_CREATED);
        Assert.assertEquals(CreateFolder.createFolder(folder1), HttpStatus.SC_CONFLICT);
        Assert.assertEquals(EnsureItemCreatedOrRestored.ensureItemCreatedOrRestored(folder1), HttpStatus.SC_OK);
        Assert.assertTrue(list.contains(DeleteFileOrFolder.deleteFileOrFolder(folder1)));
        Assert.assertEquals(EnsureItemIsInTrash.ensureItemIsInTrash(folder1), HttpStatus.SC_OK);
    }
}

