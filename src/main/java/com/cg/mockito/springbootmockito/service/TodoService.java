package com.cg.mockito.springbootmockito.service;

import java.util.List;

public interface TodoService {

	public List<String> retriveTodos(String user);

	public void deleteTodos(String doString);

}
