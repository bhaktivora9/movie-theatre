package com.jpmc.theater.biz.discount;

import com.jpmc.theater.biz.Reservation;

public class TimeSlotSpecificDiscount implements IDiscount{

	public double apply(Reservation reservation) {
		int hour = reservation.getShowing().getStartTime().getHour();
		return (hour >= 11 && hour <=16) ? (0.25*reservation.getFeeBeforeDiscount()) : 0;
	};
}
