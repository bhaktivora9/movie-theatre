package com.jpmc.theater.biz.discount;

import com.jpmc.theater.biz.Reservation;

public class SequenceDiscount implements IDiscount{

	public  double apply(Reservation reservation) {
		double sequenceDiscount = 0;
		int showSequence = reservation.getShowing().getSequenceOfTheDay();
		if (showSequence == 1) {
			sequenceDiscount = 3; // $3 discount for 1st show
		} else if (showSequence == 2) {

			sequenceDiscount = 2; // $2 discount for 2nd show
		}

		return sequenceDiscount;
	}

}
