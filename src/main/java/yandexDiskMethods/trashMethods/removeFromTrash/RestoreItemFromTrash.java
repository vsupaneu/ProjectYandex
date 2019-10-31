package yandexDiskMethods.trashMethods.removeFromTrash;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;

import static yandexDiskMethods.requestsData.RequestsData.*;

public class RestoreItemFromTrash {
    @Step("Restore file or folder from trash")
    public static int restoreItemFromTrash(String path) {
        //code 201 for restore from trash
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .queryParam(PATH, path)
                .when()
                .put(BASE_URL + RESTORE_FROM_TRASH)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .statusCode();
    }
}
