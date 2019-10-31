package yandexDiskMethods.trashMethods.trashInfoMethods;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static yandexDiskMethods.requestsData.RequestsData.*;

public class GetTrashContent {
    @Step("Get trash content")
    public static String getTrashContent() {
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .when()
                .get(BASE_URL + TRASH)
                .then().extract().response().asString();
    }
}
