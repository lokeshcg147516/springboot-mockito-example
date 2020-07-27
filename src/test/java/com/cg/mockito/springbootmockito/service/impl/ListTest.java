package com.cg.mockito.springbootmockito.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ListTest {

	@Test
	public void mockSizeListMethod_usingStub() {
		List list = mock(List.class);
		when(list.size()).thenReturn(2, 3, 4);
		assertEquals(2, list.size());
		assertEquals(3, list.size());
		assertEquals(4, list.size());
	}

	@Test
	public void mockgetListMethod_usingStub() {
		List list = mock(List.class);
		when(list.get(anyInt())).thenReturn("Test List");
		assertEquals("Test List", list.get(0));
		assertEquals("Test List", list.get(3));
	}

	@org.junit.Test
	public void mockgetListMethod_usingBDD() {
		// given
		List list = mock(List.class);
		when(list.get(anyInt())).thenReturn("Test List");

		// when
		String string0 = (String) list.get(0);
		String string3 = (String) list.get(3);
		// then

		assertEquals("Test List", string0);
		assertEquals("Test List", string3);
		assertThat(string0, is("Test List"));
	}

	@org.junit.Test(expected = RuntimeException.class)
	public void mockExceptionListMethod_usingStub() {
		List list = mock(List.class);
		when(list.get(anyInt())).thenThrow(RuntimeException.class);
		when(list.subList(anyInt(), 4)).thenReturn(Arrays.asList(1, 2, 3, 4));
		List subList = list.subList(10000, 4);
		assertEquals(Arrays.asList(1, 2, 3, 4), subList);
		assertEquals(0, list.get(3));
	}
}
