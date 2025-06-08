package com.qa.faoschwarz.api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetProductListAPI {
	private static final String BASE_URL = "https://uscs32v2.ksearchnet.com";
    private static final String ENDPOINT = "/cs/v2/search";
    
    
    
	public int getProductListNoOfResults(String searchTerm) {
		
        String requestBody = String.format("{\n \"context\": {\n \"apiKeys\": [\"klevu-168874103657216553\"]\n },\n \"recordQueries\": [\n \n {\n \"id\": \"productList\",\n \"typeOfRequest\": \"SEARCH\",\n \"settings\": {\n \"query\": {\"term\": \"%s\"},\n \"typeOfRecords\": [\"KLEVU_PRODUCT\"],\n \"limit\": 36,\n \"offset\": 0,\n \"typeOfSearch\": \"AND\",\n \"sort\": \"RELEVANCE\"\n }\n \n }\n \n \n ]\n}", searchTerm);
        
        Response response = given()
            .baseUri(BASE_URL)
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post(ENDPOINT)
        .then()
            .statusCode(200)
            .extract().response();
  
        int productCount= response.jsonPath().getInt("queryResults[0].meta.noOfResults");
        System.out.println("number of product count from API : "+ productCount);
        return productCount;
    }

}
