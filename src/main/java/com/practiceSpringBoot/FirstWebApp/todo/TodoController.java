package com.practiceSpringBoot.FirstWebApp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name") // for render that name attribute on diff jsp page with this session annotation
public class TodoController {

	@Autowired
	private TodoService todoService;

	@RequestMapping("list-todos")
	public String listAllTodods(ModelMap model) {
		String username = getLoggedInUserName(model);
		List<Todo> todo = todoService.findByUserName(username);
		model.addAttribute("todos", todo);
		return "todos";
	}

	// for adding new todos
	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedInUserName(model);
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}

	// after adding new todo redirect to main page
	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap m, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUserName(m);

		todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		// delete todo
		todoService.deleteById(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String ShowupdateTodoPage(@RequestParam int id, ModelMap model) {
		// update todo
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUserName(model);
		todo.setUsername(username);
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}