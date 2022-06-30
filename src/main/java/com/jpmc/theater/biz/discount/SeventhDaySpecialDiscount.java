package com.jpmc.theater.biz.discount;

import com.jpmc.theater.biz.Reservation;

public class SeventhDaySpecialDiscount implements IDiscount{

	public double apply(Reservation reservation) {
		return (reservation.getShowing().getStartTime().getDayOfMonth() == 7) ? 1 : 0;
	}
	
	
}
