package yandexTests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import yandexDiskMethods.diskMethods.createItem.CreateFileAndCheckCreation;
import yandexDiskMethods.diskMethods.createItem.CreateFolderAndCheckCreation;
import yandexDiskMethods.diskMethods.deleteItem.DeleteItemAndEnsureMovedToTrash;
import yandexDiskMethods.trashMethods.removeFromTrash.ClearTrash;
import yandexDiskMethods.trashMethods.trashInfoMethods.GetTrashSize;
import yandexDiskMethods.utilityMethods.BuildItemPath;
import yandexDiskMethods.utilityMethods.BuildItemPathWithFile;
import yandexDiskMethods.utilityMethods.GetRandomName;

public class TestEmbeddedFolders2 {
    @Test(description = "Create folder, embedded folder and file within. Move items to trash and clear trash.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("1. Создать папку test\n2. Внутри test создать папку foo\n3. Внутри foo создать файл autotest\n4. Поместить test в корзину\n5. Очистить корзину\n6. Проверить, что корзина очищена и папоу и файлов нет")
    public void embeddedFolders2() {
        String folder1 = GetRandomName.getRandomFolderName();
        String folder2 = GetRandomName.getRandomFolderName();
        String file1 = GetRandomName.getRandomFileName("jpeg");
        String folder2Path = BuildItemPath.buildItemPath(folder1, folder2);
        String file1Path = BuildItemPathWithFile.buildItemPathWithFile(file1, folder1, folder2);

        Assert.assertEquals(CreateFolderAndCheckCreation.createFolderAndCheckCreation(folder1), 200);
        Assert.assertEquals(CreateFolderAndCheckCreation.createFolderAndCheckCreation(folder2Path), 200);
        Assert.assertEquals(CreateFileAndCheckCreation.createFileAndCheckCreation(file1Path, folder2Path), 200);
        Assert.assertEquals(DeleteItemAndEnsureMovedToTrash.deleteItemAndEnsureMovedToTrash(folder1, folder1), 200);
        Assert.assertEquals(ClearTrash.clearTrash(), 202);
        int trashSize = GetTrashSize.getTrashSize();
        Assert.assertEquals(trashSize, 0);
    }
}
