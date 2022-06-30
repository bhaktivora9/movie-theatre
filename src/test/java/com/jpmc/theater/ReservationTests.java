package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jpmc.theater.biz.Reservation;
import com.jpmc.theater.biz.Showing;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;

public class ReservationTests {
	static Customer customer;
	static Showing showing;

	@BeforeAll
	public static void init() {
		customer = new Customer("John Doe", "unused-id");
		showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 1,
				LocalDateTime.now());
	}

	@Test
	public void getFeeBeforDiscountTest() {
		Reservation reservation = new Reservation(customer, showing, 3);
		System.out.println("Reserved !");
		assertTrue(reservation.getFeeBeforeDiscount() == 37.5);
	}

	@Test
	void getTotalFee() {
		assertTrue(new Reservation(customer, showing, 3).getTotalFee() == 30.0);
	}

	@Test
	void getReservationFail() {

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			new Reservation(customer, showing, 30);
		});
		String expectedMessage = "Sorry, try another show,there are only";
		assertTrue(exception.getMessage().contains(expectedMessage));
	}
}
