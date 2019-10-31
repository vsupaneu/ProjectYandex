package yandexDiskMethods.utilityMethods;

public class BuildItemPathWithFile {
    public static String buildItemPathWithFile(String file, String... folders) {
        StringBuilder result = new StringBuilder();
        for (String s : folders) {
            result.append(s).append("/");
        }
        return result.append(file).toString();
    }
}

