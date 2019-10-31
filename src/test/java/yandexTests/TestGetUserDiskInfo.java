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
import yandexDiskMethods.trashMethods.trashInfoMethods.GetSizesOfDeletedFiles;
import yandexDiskMethods.trashMethods.trashInfoMethods.GetTrashSize;
import yandexDiskMethods.utilityMethods.BuildItemPathWithFile;
import yandexDiskMethods.utilityMethods.GetRandomName;

public class TestGetUserDiskInfo {
    @Test(description = "Move files to trash, get trash size before and after.")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("1. Создать папку на яндекс диске\n2. Внутри созданной папки создать несколько файлов\n3. Поместить созданные файлы в корзину\n4. Посчитать размер файлов в корзине\n5. Сравнить что файлов в корзине = первоначальному разрамеру + размеру файлов в корзине\n6. Восстановить созданный файл из корзины\n7. Удалить файл и папку")
    public void getUserDiskInfo() {
        String folder1 = GetRandomName.getRandomFolderName();
        String file1 = GetRandomName.getRandomFileName("docx");
        String file2 = GetRandomName.getRandomFileName("docx");
        String file1Path = BuildItemPathWithFile.buildItemPathWithFile(file1, folder1);
        String file2Path = BuildItemPathWithFile.buildItemPathWithFile(file2, folder1);

        int initialTrashSize = GetTrashSize.getTrashSize();
        Assert.assertEquals(CreateFolderAndCheckCreation.createFolderAndCheckCreation(folder1), HttpStatus.SC_OK);
        Assert.assertEquals(CreateFileAndCheckCreation.createFileAndCheckCreation(file1Path, folder1), HttpStatus.SC_OK);
        Assert.assertEquals(CreateFileAndCheckCreation.createFileAndCheckCreation(file2Path, folder1), HttpStatus.SC_OK);
        Assert.assertEquals(DeleteItemAndEnsureMovedToTrash.deleteItemAndEnsureMovedToTrash(file1Path, file1), HttpStatus.SC_OK);
        Assert.assertEquals(DeleteItemAndEnsureMovedToTrash.deleteItemAndEnsureMovedToTrash(file2Path, file2), HttpStatus.SC_OK);
        int sizeOfDeletedFiles = GetSizesOfDeletedFiles.getSizesOfDeletedFiles(file1, file2);
        int finalTrashSize = GetTrashSize.getTrashSize();
        Assert.assertEquals(finalTrashSize, initialTrashSize + sizeOfDeletedFiles);
        Assert.assertEquals(RestoreItemFromTrashAndEnsureMovedToDisk.restoreItemFromTrashAndEnsureMovedToDisk(file1Path, file1), 200);
        Assert.assertEquals(DeleteItemAndEnsureMovedToTrash.deleteItemAndEnsureMovedToTrash(file1Path, file1), HttpStatus.SC_OK);
        Assert.assertEquals(DeleteItemAndEnsureMovedToTrash.deleteItemAndEnsureMovedToTrash(folder1, folder1), HttpStatus.SC_OK);
    }
}
