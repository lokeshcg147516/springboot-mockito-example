package com.cg.mockito.springbootmockito.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cg.mockito.springbootmockito.service.TodoService;

//TodoServiceImpl SUT
//TodoService dependency
public class TodoBusinessImpl {

	public TodoService todoService;

	public TodoBusinessImpl(TodoService todoService) {
		System.out.println("TodoBusinessImpl method called....");
		System.out.println("I got to know why i am here....");
		System.out.println("learning git");
		this.todoService = todoService;
	}

	public List<String> retriveFromTodoService(String user) {
		List<String> todoServices = new ArrayList<String>();

		List<String> todos = todoService.retriveTodos(user);

		for (String string : todos) {
			if (string.contains("Spring")) {
				todoServices.add(string);
			}
		}

		return todoServices;

	}

	public void deleteTodosNotRelatedToHibernate(String user) {

		List<String> Combinedlist = todoService.retriveTodos(user);

		for (String todos : Combinedlist) {
			if (!todos.contains("Hibernate")) {
				todoService.deleteTodos(todos);
			}
		}
	}
}
