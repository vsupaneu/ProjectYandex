package yandexDiskMethods.diskMethods.createItem;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;

import static yandexDiskMethods.requestsData.RequestsData.*;
import static yandexDiskMethods.requestsData.RequestsData.RESOURCES;

public class EnsureItemCreatedOrRestored {
    @Step("Ensure file or folder is created successfully or restored from trash")
    public static int ensureItemCreatedOrRestored(String path) {
        //code 200 for validation
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .queryParam(PATH, path)
                .when()
                .get(BASE_URL + RESOURCES)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .statusCode();
    }
}
