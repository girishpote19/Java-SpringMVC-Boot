package com.cg.springboot.firstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Base64;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {

	private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";
	private static String GET_ALL_QUESTION_URL = "/surveys/Survey1/questions";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void retriveSpecificSurveyQuestion_basicScenario() throws JSONException {
		HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();

		HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(SPECIFIC_QUESTION_URL, HttpMethod.GET, httpEntity,
				String.class);

		String expectedResponse = """
				{"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
				""";
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
		JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
	}

	@Test
	void retriveAllSurveyQuestions_get() throws JSONException {

		HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();

		HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(GET_ALL_QUESTION_URL, HttpMethod.GET, httpEntity,
				String.class);

		String expectedResponse = """
								[
				  {
				    "id": "Question1",
				    "description": "Most Popular Cloud Platform Today",
				    "options": [
				      "AWS",
				      "Azure",
				      "Google Cloud",
				      "Oracle Cloud"
				    ],
				    "correctAnswer": "AWS"
				  },
				  {
				    "id": "Question2",
				    "description": "Fastest Growing Cloud Platform",
				    "options": [
				      "AWS",
				      "Azure",
				      "Google Cloud",
				      "Oracle Cloud"
				    ],
				    "correctAnswer": "Google Cloud"
				  },
				  {
				    "id": "Question3",
				    "description": "Most Popular DevOps Tool",
				    "options": [
				      "Kubernetes",
				      "Docker",
				      "Terraform",
				      "Azure DevOps"
				    ],
				    "correctAnswer": "Kubernetes"
				  }
				]
								""";

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
		JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
	}

	@Test
	void addNewQuestion_basicScenario() {

		String requestBody = """
							{
				    "description": "Your favourite Cloud Platform Today",
				    "options": [
				        "AWS",
				        "Azure",
				        "Google Cloud",
				        "Oracle Cloud"
				    ],
				    "correctAnswer": "Google Cloud"
				}
							""";

		HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();

		HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(GET_ALL_QUESTION_URL, HttpMethod.POST, httpEntity,
				String.class);

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		String locationHeader = responseEntity.getHeaders().get("Location").get(0);
		assertTrue(locationHeader.contains("/surveys/Survey1/questions/"));

		ResponseEntity<String> responseEntityDelete = restTemplate.exchange(locationHeader, HttpMethod.DELETE,
				httpEntity, String.class);

		assertTrue(responseEntityDelete.getStatusCode().is2xxSuccessful());
	}

	private HttpHeaders createHttpContentTypeAndAuthorizationHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Basic " + performBasicAuthEncoding("girish", "Happy2024"));
		return headers;
	}

	String performBasicAuthEncoding(String user, String password) {
		String combined = user + ":" + password;
		byte[] encode = Base64.getEncoder().encode(combined.getBytes());
		return new String(encode);

	}
}
