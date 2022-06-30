package com.jpmc.theater.model;

import java.util.Objects;

public class ReserveRequest {

	Customer customer;
	String customerName;
	int howManyTickets;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getHowManyTickets() {
		return howManyTickets;
	}

	public void setHowManyTickets(int howManyTickets) {
		this.howManyTickets = howManyTickets;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, howManyTickets);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReserveRequest other = (ReserveRequest) obj;
		return Objects.equals(customer, other.customer) && howManyTickets == other.howManyTickets;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		if(this.getCustomer().getName() == null && customerName!= null )
		{
			this.customer = new Customer(customerName, customerName);
		}
		this.customerName = customerName;
	}

}
