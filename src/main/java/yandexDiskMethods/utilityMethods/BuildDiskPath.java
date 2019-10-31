package yandexDiskMethods.utilityMethods;

public class BuildDiskPath {
    public static String buildItemDiskPath(String... folders) {
        StringBuilder result = new StringBuilder();
        result.append("disk:/");
        int count = 0;
        for (String s : folders) {
            result.append(s);
            if (count == folders.length - 1) {
                break;
            }
            result.append("/");
            count++;
        }
        return result.toString();
    }
}
