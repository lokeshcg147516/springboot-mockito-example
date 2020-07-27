package com.cg.mockito.springbootmockito.service;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {

	public List<String> retriveTodos(String user) {
		return Arrays.asList("Learn Spring", "Learn SpringBoot", "Learn Python");
	}

	@Override
	public void deleteTodos(String doString) {
		// TODO Auto-generated method stub
		
	}

}
