package yandexDiskMethods.trashMethods.removeFromTrash;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;

import static yandexDiskMethods.requestsData.RequestsData.*;

public class ClearTrash {
    @Step("Clear trash")
    public static int clearTrash() {
        //code 202 for cleaning up
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .when()
                .delete(BASE_URL + TRASH)
                .then()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .extract()
                .statusCode();
    }
}
