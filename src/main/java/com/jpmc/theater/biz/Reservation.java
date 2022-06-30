package com.jpmc.theater.biz;

import com.jpmc.theater.biz.discount.Discount;
import com.jpmc.theater.model.Customer;

public class Reservation {
	private boolean status;
	private Customer customer;
	private Showing showing;
	private int audienceCount;
	private double feeBeforeDiscount;
	private double totalFee;
	public Reservation() {

	}

	public Reservation(Customer customer, boolean status) {
		this.status = status;
		this.customer = customer;

	}

	public Reservation(Customer customer, Showing showing, int audienceCount) {
		if (!showing.checkAvailabilty(audienceCount)) {
			throw new IllegalStateException("Sorry, try another show,there are only " + showing.getTicketsAvailable()+" tickets available.");
		}
		//Update ticket count
		this.customer = customer;
		this.showing = showing;
		showing.updateTicket(audienceCount);
		this.audienceCount = audienceCount;
		this.status = true;

		this.feeBeforeDiscount = calculateTicketPrice();
		this.totalFee = calculateTotalFee();
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Showing getShowing() {
		return showing;
	}

	public void setShowing(Showing showing) {
		this.showing = showing;
	}

	public int getAudienceCount() {
		return audienceCount;
	}

	public void setAudienceCount(int audienceCount) {
		this.audienceCount = audienceCount;
	}

	public double getFeeBeforeDiscount() {
		return feeBeforeDiscount;
	}

	public void setFeeBeforeDiscount(double feeBeforeDiscount) {
		this.feeBeforeDiscount = feeBeforeDiscount;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	private double calculateTicketPrice() {
		return showing.getMovieFee() * audienceCount;
	}

	private double calculateTotalFee() {
		double maxDiscountApplied = Discount.applyAllDiscounts(this);
		return feeBeforeDiscount - maxDiscountApplied;
	}

}