package yandexDiskMethods.diskMethods.createItem;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.httpclient.HttpStatus;

import static yandexDiskMethods.requestsData.RequestsData.*;
import static yandexDiskMethods.requestsData.RequestsData.PATH;

public class CreateFile {
    @Step("Create file")
    public static int createFile(String path, String destinationPath) {
        String href = GetLinkForFileUpload.getLinkForFileUpload(path);

        //code 201 for file creation
        return RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(AUTHORIZATION, TOKEN)
                .queryParam(PATH, destinationPath)
                .when()
                .put(href)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .statusCode();
    }
}
