package com.cg.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SomeBusinessImplTest {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Test
	void findTheGreatestFromAllData_test() {

		when(dataServiceMock.retriveAllData()).thenReturn(new int[] { 25, 15, 5 });
		assertEquals(25, businessImpl.findTheFreatestFromAllData());
	}

	@Test
	void findTheGreatestFromAllData_OneValue_test() {

		when(dataServiceMock.retriveAllData()).thenReturn(new int[] { 35 });
		assertEquals(35, businessImpl.findTheFreatestFromAllData());
	}
	
	@Test
	void findTheGreatestFromAllData_Empty_test() {

		when(dataServiceMock.retriveAllData()).thenReturn(new int[] { });
		assertEquals(Integer.MIN_VALUE, businessImpl.findTheFreatestFromAllData());
	}
}
