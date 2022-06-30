package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.biz.Reservation;
import com.jpmc.theater.biz.Theater;
import com.jpmc.theater.model.Customer;

public class TheaterTests {

	@Test
	void totalFeeForCustomer() {
		Theater theater = new Theater();
		Customer john = new Customer("John Doe", "id-12345");
		Reservation reservation = theater.reserve(john, 2, 4);
        System.out.println("You have to pay " + reservation.getTotalFee());
		assertEquals(reservation.getTotalFee(), 40);
	}

	@Test
	void printMovieSchedule() {
		Theater theater = new Theater();
		theater.printSchedule();
	}
}
