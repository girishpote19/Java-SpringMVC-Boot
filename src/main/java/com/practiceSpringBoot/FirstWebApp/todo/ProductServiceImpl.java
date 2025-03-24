package com.practiceSpringBoot.FirstWebApp.todo;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public List<String> getAllProductNames() {
		return Arrays.asList("Product1", "Product2", "Product3");
	}
}