package yandexDiskMethods.diskMethods.createItem;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;

import static yandexDiskMethods.requestsData.RequestsData.*;

public class GetLinkForFileUpload {
    @Step("Get link for file upload")
    public static String getLinkForFileUpload(String filePath) {
        //code 200 for link
        return
                RestAssured.given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .header(AUTHORIZATION, TOKEN)
                        .queryParam(PATH, filePath)
                        .when()
                        .get(BASE_URL + UPLOAD_FILE)
                        .then()
                        .statusCode(HttpStatus.SC_OK )
                        .extract().path(HREF);
    }
}
