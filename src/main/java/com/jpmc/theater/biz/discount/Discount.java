package com.jpmc.theater.biz.discount;

import com.jpmc.theater.biz.Reservation;

public class Discount {
	static IDiscount sequenceDiscount;
	static IDiscount specialMovieCodeDiscount;
	static IDiscount seventhDaySpecialDiscount;
	static IDiscount timeSlotSpecificDiscount;

	public static void init() {
		if (sequenceDiscount == null)
			sequenceDiscount = new SequenceDiscount();
		if (specialMovieCodeDiscount == null)
			specialMovieCodeDiscount = new SpecialMovieCodeDiscount();
		if (seventhDaySpecialDiscount == null)
			seventhDaySpecialDiscount = new SeventhDaySpecialDiscount();
		if (timeSlotSpecificDiscount == null)
			timeSlotSpecificDiscount = new TimeSlotSpecificDiscount();
	}

	public static double applyAllDiscounts(Reservation reservation) {
		init();
		double sequenceDisc = sequenceDiscount.apply(reservation);
		double specialDisc = specialMovieCodeDiscount.apply(reservation);
		double maxDiscount = (specialDisc > sequenceDisc ? specialDisc : sequenceDisc);
		double seventhDaySpecialDisc = seventhDaySpecialDiscount.apply(reservation);
		double timeSpecificDisc = timeSlotSpecificDiscount.apply(reservation);
		maxDiscount = (maxDiscount > seventhDaySpecialDisc) ? maxDiscount : seventhDaySpecialDisc;
		maxDiscount = (maxDiscount > timeSpecificDisc) ? maxDiscount : timeSpecificDisc;

		return maxDiscount;
	}

}
