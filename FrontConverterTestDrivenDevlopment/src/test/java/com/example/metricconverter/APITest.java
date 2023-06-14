package com.example.metricconverter;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpStatus;

import com.example.metricconverter.constant.MetricConverterTddEnum;
import com.example.metricconverter.exception.BothInputRequestParamEqualException;

public class APITest {

	
	@Test
	@DisplayName("testing service chaining call")
	public void testGivenEnumFromUnitAndToUnit_whenBothInputsAreDifferent_thenWillGetCalculatedResult() {

		RestAssured.baseURI = "http://localhost:8089";
		String flow1_To_Flow2_TestResult = given().log().all().when()
				.queryParam("fromEnumUnit", MetricConverterTddEnum.METER.toString())
				.queryParam("toEnumUnit", MetricConverterTddEnum.CM.toString()).queryParam("value", "9")
				.get("/converter").then().assertThat().log().all().statusCode(200).extract().response().asString();

		System.out.println("flow1 To Flow2 TestResult::" + flow1_To_Flow2_TestResult);

	}

	@Test
	public void testGivenEnumFromUnitAndToUnit_whenBothInputsAreSame_thenWillGetCalculatedResult() throws BothInputRequestParamEqualException {

		RestAssured.baseURI = "http://localhost:8089";
		String flow1_To_Flow2_TestResult = given().log().all().when()
				.queryParam("fromEnumUnit", MetricConverterTddEnum.METER.toString())
				.queryParam("toEnumUnit", MetricConverterTddEnum.METER.toString()).queryParam("value", "9")
				.get("/converter").then().assertThat().log().all().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response().asString();

		System.out.println("flow1 To Flow2 TestResult::" + flow1_To_Flow2_TestResult);
		Assert.assertEquals("Both input is same which is not allowed",flow1_To_Flow2_TestResult);

	}

}
