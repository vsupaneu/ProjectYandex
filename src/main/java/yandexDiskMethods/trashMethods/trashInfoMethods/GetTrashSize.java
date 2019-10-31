package yandexDiskMethods.trashMethods.trashInfoMethods;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static yandexDiskMethods.requestsData.RequestsData.*;

public class GetTrashSize {
    @Step("Get trash size")
    public static int getTrashSize() {
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .when()
                .get(BASE_URL)
                .then()
                .extract().path(TRASH_SIZE);
    }
}
