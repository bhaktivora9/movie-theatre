package com.jpmc.theater.biz.discount;

import com.jpmc.theater.biz.Reservation;
import com.jpmc.theater.model.Movie;

public class SpecialMovieCodeDiscount implements IDiscount{

	public double apply(Reservation reservation) {

		Movie movie = reservation.getShowing().getMovie();

		double specialDiscount = movie.isSpecialMovie() ? reservation.getFeeBeforeDiscount() * 0.2 : 0;
		// Apply 20% discount
		// for special
		return specialDiscount;
	}

}
