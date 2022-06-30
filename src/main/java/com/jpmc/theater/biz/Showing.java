package com.jpmc.theater.biz;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.jpmc.theater.model.Movie;

@Component
public class Showing {
	private Movie movie;
	private int sequenceOfTheDay;
	private LocalDateTime showStartTime;
	// Adding tickets available
	private int ticketsAvailable = 10;

	public Showing() {

	}

	public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime, int ticketsAvailable) {
		this.movie = movie;
		this.sequenceOfTheDay = sequenceOfTheDay;
		this.showStartTime = showStartTime;
	}

	public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
		this.movie = movie;
		this.sequenceOfTheDay = sequenceOfTheDay;
		this.showStartTime = showStartTime;
	}

	public Movie getMovie() {
		return movie;
	}

	public LocalDateTime getStartTime() {
		return showStartTime;
	}

	public boolean isSequence(int sequence) {
		return this.sequenceOfTheDay == sequence;
	}

	public double getMovieFee() {
		return movie.getTicketPrice();
	}

	public int getSequenceOfTheDay() {
		return sequenceOfTheDay;
	}

	public int getTicketsAvailable() {
		return ticketsAvailable;
	}

	public void setTicketsAvailable(int ticketsAvailable) {
		this.ticketsAvailable = ticketsAvailable;
	}

	public void updateTicket(int ticketsBooked) {
		this.ticketsAvailable = this.ticketsAvailable - ticketsBooked;
	}

	public boolean checkAvailabilty(int ticketsBooked) {
		return (this.ticketsAvailable - ticketsBooked >= 0);
	}
}
