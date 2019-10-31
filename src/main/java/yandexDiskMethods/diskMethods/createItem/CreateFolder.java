package yandexDiskMethods.diskMethods.createItem;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;

import static yandexDiskMethods.requestsData.RequestsData.*;
import static yandexDiskMethods.requestsData.RequestsData.RESOURCES;

public class CreateFolder {
    @Step("Create folder")
    public static int createFolder(String folderPath) {
        //code 201 for creation
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .queryParam(PATH, folderPath)
                .when()
                .put(BASE_URL + RESOURCES)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .statusCode();
    }
}
