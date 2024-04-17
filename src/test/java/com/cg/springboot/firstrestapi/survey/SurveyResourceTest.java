package com.cg.springboot.firstrestapi.survey;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = SurveyResource.class)
@AutoConfigureMockMvc(addFilters = false)

class SurveyResourceTest {

	@MockBean
	private SurveyService surveyService;

	@Autowired
	private MockMvc mockMvc;

	private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";
	private static String GET_ALL_QUESTION_URL = "/surveys/Survey1/questions";

	@Test
	void retriveSpecificSurveyQuestion_404Scenario() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(404, response.getStatus());
	}

	@Test
	void retriveSpecificSurveyQuestion_basicScenario() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL)
				.accept(MediaType.APPLICATION_JSON);

		Question question = new Question("Question1", "Most Popular Cloud Platform Today",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");

		when(surveyService.retriveSurveyQuestions("Survey1", "Question1")).thenReturn(question);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		String expectedResponse = """
				{"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
				""";
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(200, response.getStatus());
		JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false);
	}

	@Test
	void addNewSurveyQuestion_basicScenario() throws Exception {

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

		when(surveyService.addNewQuestion(anyString(), any())).thenReturn("SOME_ID");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(GET_ALL_QUESTION_URL)
				.accept(MediaType.APPLICATION_JSON).content(requestBody).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		String header = response.getHeader("Location");

		assertEquals(201, response.getStatus());
		assertTrue(header.contains("/surveys/Survey1/questions/SOME_ID"));
	}

}
