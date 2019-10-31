package yandexDiskMethods.utilityMethods;

public class BuildItemPath {
    public static String buildItemPath(String... folders) {
        StringBuilder result = new StringBuilder();
        for (String s : folders) {
            result.append(s).append("/");
        }
        return result.toString();
    }
}
