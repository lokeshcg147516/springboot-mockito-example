package com.cg.mockito.springbootmockito.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
public class SpyList {

	@Test
	public void spyList() {
		List list = spy(List.class);
		when(list.size()).thenReturn(10);
		assertEquals(10, list.size());
		list.add(100);
		assertEquals(10, list.size());
		verify(list, atLeast(1)).add(100);
		
		
	}
}
