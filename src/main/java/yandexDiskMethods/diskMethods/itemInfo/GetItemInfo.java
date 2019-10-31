package yandexDiskMethods.diskMethods.itemInfo;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.equalTo;
import static yandexDiskMethods.requestsData.RequestsData.*;

public class GetItemInfo {
    @Step("Get item info")
    public static void checkFolderInfo(int amountOfEmbeddedItems, String folderPath, String folderName) {
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .queryParam(PATH, folderPath)
                .expect()
                .body(EMBEDDED_TOTAL, equalTo(amountOfEmbeddedItems))
                .body(EMBEDDED_PATH, equalTo(folderPath))
                .body(NAME, equalTo(folderName))
                .statusCode(200)
                .when()
                .get(BASE_URL + RESOURCES);
    }
}
