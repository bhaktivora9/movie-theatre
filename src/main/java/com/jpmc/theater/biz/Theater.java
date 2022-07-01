package com.jpmc.theater.biz;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.util.LocalDateProvider;

@Component
public class Theater {

	LocalDateProvider provider;

	private List<Showing> schedule;

	public Theater() {
		init();
	}

	private void init() {
		this.provider = LocalDateProvider.singleton();

		Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
		Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
		Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);

		schedule = List.of(new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
				new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
				new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
				new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
				new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
				new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
				new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
				new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
				new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0))));

	}

	public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
		Showing showing;
		try {
			showing = schedule.get(sequence - 1);
			return new Reservation(customer, showing, howManyTickets);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw new IllegalStateException("No Show found for sequence id" + sequence);
		}

	}

	public void printSchedule() {
		System.out.println(provider.currentDate());
		System.out.println("===================================================");
		schedule.forEach(s -> System.out
				.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " "
						+ humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee()));
		System.out.println("===================================================");
	}

	public String getScheduleAsText() {
		List<String> list = new ArrayList<>();
		list.add(provider.currentDate().toString());
		schedule.stream().forEach(s -> {
			String temp = s.getSequenceOfTheDay() + ": " + s.getStartTime().format(DateTimeFormatter.BASIC_ISO_DATE)
					+ " " + s.getStartTime().format(DateTimeFormatter.ISO_LOCAL_TIME) + "	" + s.getMovie().getTitle()
					+ " 	" + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee();
			list.add(temp);
		});
		StringBuilder sb = new StringBuilder();
		sb.append(list.stream().collect(Collectors.joining("<br>")));
		return sb.toString();
	}

	public List<Showing> getScheduleAsJSON() {
		return this.schedule;
	}

	public String humanReadableFormat(Duration duration) {
		long hour = duration.toHours();
		long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

		return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin,
				handlePlural(remainingMin));
	}

	// (s) postfix should be added to handle plural correctly
	private String handlePlural(long value) {
		if (value == 1) {
			return "";
		} else {
			return "s";
		}
	}

	/*
	 * public static void main(String[] args) { Theater theater = new
	 * Theater(LocalDateProvider.singleton()); theater.printSchedule(); }
	 */
}
