package com.cg.springboot.firstrestapi.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {

	public UserDetailsCommandLineRunner(UserDetailsRepository userDetailsRepository) {
		super();
		this.repository = userDetailsRepository;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	private UserDetailsRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.save(new UserDetails("girish", "user"));
		repository.save(new UserDetails("harish", "admin"));
		repository.save(new UserDetails("nick", "user"));

		//List<UserDetails> users = repository.findAll();
		List<UserDetails> users = repository.findByRole("user");
		users.forEach(user -> logger.info(user.toString()));
	}

}
