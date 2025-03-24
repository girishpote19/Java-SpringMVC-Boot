package com.practiceSpringBoot.FirstWebApp.todo;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ParameterServiceImpl implements ParameterService {

	@Override
	public List<String> getAllLanguages() {
		return Arrays.asList("English", "French", "Spanish");
	}
}
