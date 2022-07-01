package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jpmc.theater.biz.Reservation;
import com.jpmc.theater.biz.Theater;
import com.jpmc.theater.model.Customer;

public class TheaterTests {
	static Theater theater;

	@BeforeAll
	public static void init() {
		theater = new Theater();
	}

	@Test
	void reserveSuccess() {
		Customer john = new Customer("John Doe", "id-12345");
		Reservation reservation = theater.reserve(john, 2, 4);
		System.out.println("You have to pay " + reservation.getTotalFee());
		assertEquals(reservation.getTotalFee(), 37.5);
	}

	@Test
	void printMovieSchedule() {
		theater.printSchedule();
	}
	@Test
	void getScheduleAsText() {
		theater.getScheduleAsText();
	}

	@Test
	void printMovieScheduleAsJson() {
		theater.getScheduleAsJSON();
	}
	
	@Test
	void reserveFailure() {
		Customer customer = new Customer("John Doe", "id-12345");
		Exception ex = assertThrows(IllegalStateException.class, ()->theater.reserve(customer, 10, 2));
		assertTrue(ex.getMessage().contains("No Show found for sequence"));
	}
}
