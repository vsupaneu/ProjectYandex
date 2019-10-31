package yandexDiskMethods.trashMethods.removeFromTrash;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;

import static yandexDiskMethods.requestsData.RequestsData.*;

public class EnsureItemIsInTrash {
    @Step("Ensure item is in trash")
    public static int ensureItemIsInTrash(String path) {
        //code 200 for validation
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .queryParam(PATH, path)
                .when()
                .get(BASE_URL + TRASH)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().statusCode();
    }
}
