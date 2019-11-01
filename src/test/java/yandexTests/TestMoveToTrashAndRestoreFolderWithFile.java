package yandexTests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.commons.httpclient.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import yandexDiskMethods.diskMethods.createItem.CreateFileAndCheckCreation;
import yandexDiskMethods.diskMethods.createItem.CreateFolderAndCheckCreation;
import yandexDiskMethods.diskMethods.deleteItem.DeleteItemAndEnsureMovedToTrash;
import yandexDiskMethods.trashMethods.removeFromTrash.RestoreItemFromTrashAndEnsureMovedToDisk;
import yandexDiskMethods.utilityMethods.BuildItemPathWithFile;
import yandexDiskMethods.utilityMethods.GetRandomName;

public class TestMoveToTrashAndRestoreFolderWithFile {
    @Test(description = "Create folder and file within. Move file to trash and restore back.")
    @Severity(SeverityLevel.MINOR)
    @Description("1. Создать папку на яндекс диске\n2. Внутри созданной папки создать файл\n3. Поместить созданный файл в корзину\n4. Восстановить созданный файл из корзины\n5. Удалить файл и папку")
    public void moveToTrashAndRestoreFolderWithFile() {
        String folder1 = GetRandomName.getRandomFolderName();
        String file1 = GetRandomName.getRandomFileName("png");
        String file1Path = BuildItemPathWithFile.buildItemPathWithFile(file1, folder1);
//comment to trigger webhook
        Assert.assertEquals(CreateFolderAndCheckCreation.createFolderAndCheckCreation(folder1), HttpStatus.SC_OK);
        Assert.assertEquals(CreateFileAndCheckCreation.createFileAndCheckCreation(file1Path, folder1), HttpStatus.SC_OK);
        Assert.assertEquals(DeleteItemAndEnsureMovedToTrash.deleteItemAndEnsureMovedToTrash(file1Path, file1), HttpStatus.SC_OK);
        Assert.assertEquals(RestoreItemFromTrashAndEnsureMovedToDisk.restoreItemFromTrashAndEnsureMovedToDisk(file1Path, file1),HttpStatus.SC_OK);
        Assert.assertEquals(DeleteItemAndEnsureMovedToTrash.deleteItemAndEnsureMovedToTrash(file1Path, file1), HttpStatus.SC_OK);
        Assert.assertEquals(DeleteItemAndEnsureMovedToTrash.deleteItemAndEnsureMovedToTrash(folder1, folder1), HttpStatus.SC_OK);
    }
}
