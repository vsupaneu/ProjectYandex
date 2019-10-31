package yandexDiskMethods.diskMethods.deleteItem;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;

import static org.hamcrest.Matchers.isOneOf;
import static yandexDiskMethods.requestsData.RequestsData.*;
import static yandexDiskMethods.requestsData.RequestsData.RESOURCES;

public class DeleteFileOrFolder {
    @Step("Delete file or folder")
    public static int deleteFileOrFolder(String path) {
        //codes 202 and 204 for successful deletion
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .queryParam(PATH, path)
                .when()
                .delete(BASE_URL + RESOURCES)
                .then()
                .statusCode(isOneOf(HttpStatus.SC_ACCEPTED, HttpStatus.SC_NO_CONTENT))
                .extract()
                .statusCode();
    }
}
