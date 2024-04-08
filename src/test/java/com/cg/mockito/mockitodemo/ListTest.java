package com.cg.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ListTest {

	@Test
	void simpleTest() {

		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(3);
		assertEquals(3, listMock.size());
	}

	@Test
	void multipleReturnTest() {

		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(1).thenReturn(2);
		assertEquals(1, listMock.size());
		assertEquals(2, listMock.size());
	}

	@Test
	void parametersTest() {

		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("some string");
		assertEquals("some string", listMock.get(0));
	}
	
	@Test
	void anyparametersTest() {

		List listMock = mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn("someOtherstring"); //get any mnje kontahi parameter
		assertEquals("someOtherstring", listMock.get(0));
		assertEquals("someOtherstring", listMock.get(1));
	}
}
