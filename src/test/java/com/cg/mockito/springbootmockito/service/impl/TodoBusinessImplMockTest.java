package com.cg.mockito.springbootmockito.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.cg.mockito.springbootmockito.service.TodoService;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockTest {
	@Mock
	TodoService todoService;
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	@Captor
	ArgumentCaptor<String> argumentCaptor;
	

	// @Before
	// public void init() {
	// MockitoAnnotations.initMocks(this);
	// }

	@Test
	public void testTodoBusinessImpl_usingTest() {
		when(todoService.retriveTodos("Dummy"))
				.thenReturn(Arrays.asList("Learn Spring", "Learn SpringBoot", "Learn Python"));
		List<String> list = todoBusinessImpl.retriveFromTodoService("Dummy");
		assertNotNull(list);
		assertEquals(2, list.size());
		list = list.stream().filter(p -> p.contains("Spring")).collect(Collectors.toList());
		assertTrue(list.size() > 0);
	}

	@Test
	public void testTodoBusinessImpl_usingEmptyLstTest() {
		when(todoService.retriveTodos("Dummy")).thenReturn(Arrays.asList());
		List<String> list = todoBusinessImpl.retriveFromTodoService("Dummy");
		assertNotNull(list);
		assertEquals(0, list.size());
		list = list.stream().filter(p -> p.contains("Spring")).collect(Collectors.toList());
		assertTrue(list.size() == 0);
	}

	@Test
	public void testTodoBusinessImpl_usingBDD() {
		// given
		given(todoService.retriveTodos("Dummy")).willReturn(Arrays.asList());

		// when
		List<String> list = todoBusinessImpl.retriveFromTodoService("Dummy");
		list = list.stream().filter(p -> p.contains("Spring")).collect(Collectors.toList());

		// then
		assertNotNull(list);
		assertThat(list.size(), is(0));
		assertEquals(0, list.size());
		assertTrue(list.size() == 0);
	}

	@Test
	public void deleteTodosusing_BDD_usingArgumentCaptor() {

		// Declare an Argument Captor
	//	ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

		// Given

		List<String> combinedlist = Arrays.asList("Use Hibernate Java", "Use Hibernate Core", "Use Hibernate",
				"Use Spring MVC");

		given(todoService.retriveTodos("dummy")).willReturn(combinedlist);


		// When
		todoBusinessImpl.deleteTodosNotRelatedToHibernate("dummy");

		// Then
		then(todoService).should().deleteTodos(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue(), is("Use Spring MVC"));
	}
	
	
	@Test
	public void letsTestDeleteNow() {


		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.retriveTodos("Ranga")).thenReturn(allTodos);


		todoBusinessImpl.deleteTodosNotRelatedToHibernate("Ranga");

		verify(todoService).deleteTodos("Learn to Dance");

		//verify(todoService, never()).deleteTodos("Learn Spring MVC");

	//	verify(todoService, never()).deleteTodos("Learn Spring");

		verify(todoService, times(1)).deleteTodos("Learn to Dance");
		// atLeastOnce, atLeast

	}
	
	@Test
	public void hamcrestTest() {
		List<Integer> integers=Arrays.asList(1,3,4);
		
		assertThat(integers.size(), is(3));
		assertThat(integers.isEmpty(), is(false));
	}
	
	@Test
	public void thenAnswerTest(){
		when(todoBusinessImpl.retriveFromTodoService("Dummy")).thenAnswer(new Answer<List>() {

			@Override
			public List answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				
				return Arrays.asList("Ramu");
			}
		});
		
		assertEquals(Arrays.asList("Ramu"), todoBusinessImpl.retriveFromTodoService("Dummy"));
	}
	
}
