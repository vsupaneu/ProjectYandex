package yandexTests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.commons.httpclient.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import yandexDiskMethods.diskMethods.createItem.CreateFile;
import yandexDiskMethods.diskMethods.createItem.CreateFolder;
import yandexDiskMethods.diskMethods.createItem.EnsureItemCreatedOrRestored;
import yandexDiskMethods.diskMethods.deleteItem.DeleteItemAndEnsureMovedToTrash;
import yandexDiskMethods.utilityMethods.BuildItemPathWithFile;
import yandexDiskMethods.utilityMethods.GetRandomName;

public class TestCreateFolderWithFileAndDeleteBoth {
    @Test(
            description = "Create folder and file within, delete both."
    )
    @Severity(SeverityLevel.MINOR)
    @Description("1. Создать папку на яндекс диске\n2. Внутри созданной папки создать файл\n3. Удалить созданный файл\n4. Удалть созданную папку")
    public void createFolderWithFileAndDeleteBoth() {
        String folder1 = GetRandomName.getRandomFolderName();
        String file1 = GetRandomName.getRandomFileName("jpg");
        String filePath = BuildItemPathWithFile.buildItemPathWithFile(file1, folder1);
//gaga
        Assert.assertEquals(CreateFolder.createFolder(folder1), HttpStatus.SC_CREATED);
        Assert.assertEquals(EnsureItemCreatedOrRestored.ensureItemCreatedOrRestored(folder1), HttpStatus.SC_OK);
        Assert.assertEquals(CreateFile.createFile(filePath, folder1), HttpStatus.SC_CREATED);
        Assert.assertEquals(EnsureItemCreatedOrRestored.ensureItemCreatedOrRestored(filePath), HttpStatus.SC_OK);
        Assert.assertEquals(DeleteItemAndEnsureMovedToTrash.deleteItemAndEnsureMovedToTrash(filePath, file1), HttpStatus.SC_OK);
        Assert.assertEquals(DeleteItemAndEnsureMovedToTrash.deleteItemAndEnsureMovedToTrash(folder1, folder1), HttpStatus.SC_OK);
    }
}
