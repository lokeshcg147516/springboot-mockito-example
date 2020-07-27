package com.cg.mockito.springbootmockito.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.cg.mockito.springbootmockito.service.TodoService;
import com.cg.mockito.springbootmockito.service.TodoServiceStub;

public class TodoBusinessImplTest {
	@Test
	public void testTodoBusinessImpl_usingTest() {
		TodoService todoService = new TodoServiceStub();
		TodoBusinessImpl businessImpl = new TodoBusinessImpl(todoService);

		List<String> list = businessImpl.retriveFromTodoService("ram");
		assertNotNull(list);
		assertEquals(2, list.size());
		list=list.stream().filter(p -> p.contains("Spring")).collect(Collectors.toList());
		assertTrue(list.size()>0);
	}
}
 