package yandexDiskMethods.trashMethods.trashInfoMethods;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static yandexDiskMethods.requestsData.RequestsData.*;

public class GetItemsCount {
    @Step("Get items count")
    public static int getItemsCount() {
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .when()
                .get(BASE_URL + TRASH)
                .then()
                .extract().path(EMBEDDED_TOTAL);
    }
}
