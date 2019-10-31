package yandexDiskMethods.utilityMethods;

import java.util.Random;

public class GetRandomName {
    public static String getRandomFolderName() {
        Random random = new Random();
        return "Folder" + random.nextInt(1_000_000);
    }

    public static String getRandomFileName(String fileExtenstion) {
        Random random = new Random();
        return "File" + random.nextInt(1_000_000) + "." + fileExtenstion;
    }
}
