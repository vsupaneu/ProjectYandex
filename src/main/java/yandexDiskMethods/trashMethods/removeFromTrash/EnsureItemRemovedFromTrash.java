package yandexDiskMethods.trashMethods.removeFromTrash;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;

import static yandexDiskMethods.requestsData.RequestsData.*;

public class EnsureItemRemovedFromTrash {
    @Step("Ensure item is removed from trash")
    public int ensureItemRemovedFromTrash(String path) {
        //code 404 for removal from trash
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .queryParam(PATH, path)
                .when()
                .get(BASE_URL + TRASH)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .statusCode();
    }
}
