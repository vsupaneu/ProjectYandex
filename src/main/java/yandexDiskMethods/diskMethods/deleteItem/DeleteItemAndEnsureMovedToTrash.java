package yandexDiskMethods.diskMethods.deleteItem;

import io.qameta.allure.Step;
import org.apache.commons.httpclient.HttpStatus;
import org.testng.Assert;
import yandexDiskMethods.trashMethods.removeFromTrash.EnsureItemIsInTrash;
import yandexDiskMethods.trashMethods.trashInfoMethods.GetItemsCount;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class DeleteItemAndEnsureMovedToTrash {
    @Step("Delete item and ensure it was moved to trash")
    public static int deleteItemAndEnsureMovedToTrash(String path,  String name) {
        //codes 202, 204 for deletion, 404 - ensureItemIsDeleted, 200 - EnsureItemIsInTrash
        int trashSize = GetItemsCount.getItemsCount();
        ArrayList<Integer> codesDelete = new ArrayList<>();
        codesDelete.add(HttpStatus.SC_ACCEPTED);
        codesDelete.add(HttpStatus.SC_NO_CONTENT);
        int newSize = 0;

        Assert.assertTrue(codesDelete.contains(DeleteFileOrFolder.deleteFileOrFolder(path)));

/*        for (int i = 0; i < 10; i++) {
            newSize = GetItemsCount.getItemsCount();
            if ((trashSize + 1 == newSize))
                break;
        }*/

        await().atMost(60, TimeUnit.SECONDS).pollInterval(5, TimeUnit.SECONDS).until(() -> GetItemsCount.getItemsCount() == (trashSize + 1));
        Assert.assertEquals(EnsureItemIsDeleted.ensureItemIsDeleted(path), HttpStatus.SC_NOT_FOUND);
        return EnsureItemIsInTrash.ensureItemIsInTrash(name);
    }
}
