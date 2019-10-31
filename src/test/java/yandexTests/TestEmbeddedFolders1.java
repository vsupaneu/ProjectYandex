package yandexTests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import yandexDiskMethods.diskMethods.createItem.CreateFileAndCheckCreation;
import yandexDiskMethods.diskMethods.createItem.CreateFolderAndCheckCreation;
import yandexDiskMethods.diskMethods.deleteItem.DeleteItemAndEnsureMovedToTrash;
import yandexDiskMethods.diskMethods.deleteItem.EnsureItemIsDeleted;
import yandexDiskMethods.diskMethods.itemInfo.GetItemInfo;
import yandexDiskMethods.utilityMethods.BuildDiskPath;
import yandexDiskMethods.utilityMethods.BuildItemPath;
import yandexDiskMethods.utilityMethods.BuildItemPathWithFile;
import yandexDiskMethods.utilityMethods.GetRandomName;

public class TestEmbeddedFolders1 {
    @Test(description = "Create folder, embedded folder and file within. Get meta data and delete items.")
    @Severity(SeverityLevel.BLOCKER)
    @Description("1. Создать папку test\n2. Внутри test создать папку foo\n3. Внутри foo создать файл autotest\n4. Получить метаданные test и сравнить что и тип параметров соответсвует ожидаемому\n5. Удалить папку тест\n6. Проверить ,что удалилась папку test, foo и файл autotest")
    public void embeddedFolders1() {
        String folder1 = GetRandomName.getRandomFolderName();
        String folder2 = GetRandomName.getRandomFolderName();
        String file1 = GetRandomName.getRandomFileName("docx");
        String folder1DiskPath = BuildDiskPath.buildItemDiskPath(folder1);
        String folder2Path = BuildItemPath.buildItemPath(folder1, folder2);
        String file1Path = BuildItemPathWithFile.buildItemPathWithFile(file1, folder1, folder2);
        int amountOfEmbeddedItems = 1;

        Assert.assertEquals(CreateFolderAndCheckCreation.createFolderAndCheckCreation(folder1), 200);
        Assert.assertEquals(CreateFolderAndCheckCreation.createFolderAndCheckCreation(folder2Path), 200);
        Assert.assertEquals(CreateFileAndCheckCreation.createFileAndCheckCreation(file1Path, folder2Path), 200);
        GetItemInfo.checkFolderInfo(amountOfEmbeddedItems, folder1DiskPath, folder1);
        Assert.assertEquals(DeleteItemAndEnsureMovedToTrash.deleteItemAndEnsureMovedToTrash(folder1, folder1), 200);
        Assert.assertEquals(EnsureItemIsDeleted.ensureItemIsDeleted(folder2Path), 404);
        Assert.assertEquals(EnsureItemIsDeleted.ensureItemIsDeleted(file1Path), 404);
    }
}
