package yandexDiskMethods.diskMethods.deleteItem;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;

import static yandexDiskMethods.requestsData.RequestsData.*;
import static yandexDiskMethods.requestsData.RequestsData.RESOURCES;

public class EnsureItemIsDeleted {
    @Step("Ensure item is deleted from disk")
    public static int ensureItemIsDeleted(String path) {
        //code 404 for validation
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .queryParam(PATH, path)
                .when()
                .get(BASE_URL + RESOURCES)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .statusCode();
    }
}
