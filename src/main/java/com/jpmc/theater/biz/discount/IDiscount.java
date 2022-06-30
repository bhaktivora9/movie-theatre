package com.jpmc.theater.biz.discount;

import com.jpmc.theater.biz.Reservation;

public interface IDiscount {
	public double apply(Reservation reservation) ;

}
