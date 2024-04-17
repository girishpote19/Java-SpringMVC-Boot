package com.cg.springboot.firstrestapi.survey;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class SurveyService {

	private static List<Survey> surveys = new ArrayList<>();

	static {

		Question question1 = new Question("Question1", "Most Popular Cloud Platform Today",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		Question question2 = new Question("Question2", "Fastest Growing Cloud Platform",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
		Question question3 = new Question("Question3", "Most Popular DevOps Tool",
				Arrays.asList("Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

		List<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3));

		Survey survey = new Survey("Survey1", "My Favorite Survey", "Description of the Survey", questions);

		surveys.add(survey);

	}

	public Object retriveAllSurveys;

	public List<Survey> retriveAllSurveys() {
		// TODO Auto-generated method stub
		return surveys;
	}

	public Survey retriveSurveysById(String surveyId) {

		Predicate<? super Survey> predicate = survey -> survey.getId().equalsIgnoreCase(surveyId);

		Optional<Survey> optionalSurvey = surveys.stream().filter(predicate).findFirst();

		if (optionalSurvey.isEmpty())
			return null;
		return optionalSurvey.get();
	}

	public List<Question> retriveSurveyQuestions(String surveyId) {
		Survey survey = retriveSurveysById(surveyId);

		if (survey == null)
			return null;
		return survey.getQuestions();
	}

	public Question retriveSurveyQuestions(String surveyId, String questionId) {
		List<Question> SurveyQuestions = retriveSurveyQuestions(surveyId);

		if (SurveyQuestions == null)
			return null;

		Optional<Question> optionalQuestion = SurveyQuestions.stream()
				.filter(q -> q.getId().equalsIgnoreCase(questionId)).findFirst();

		if (optionalQuestion == null)
			return null;

		return optionalQuestion.get();
	}

	public String addNewQuestion(String surveyId, Question question) {
		List<Question> questions = retriveSurveyQuestions(surveyId);
		question.setId(generateRandomId());
		questions.add(question);
		return question.getId();
	}

	private String generateRandomId() {
		SecureRandom secureRandom = new SecureRandom();
		String randomId = new BigInteger(32, secureRandom).toString();
		return randomId;
	}

	public String deleteSurveyQuestion(String surveyId, String questionId) {
		List<Question> SurveyQuestions = retriveSurveyQuestions(surveyId);

		if (SurveyQuestions == null)
			return null;

		Predicate<? super Question> predicate = q -> q.getId().equalsIgnoreCase(questionId);
		boolean removed = SurveyQuestions.removeIf(predicate);

		if (!removed)
			return null;

		return questionId;

	}

	public void updateSurveyQuestion(String surveyId, String questionId, Question question) {
		List<Question> SurveyQuestions = retriveSurveyQuestions(surveyId);
		SurveyQuestions.removeIf(q -> q.getId().equalsIgnoreCase(questionId));
		SurveyQuestions.add(question);
	}
}
