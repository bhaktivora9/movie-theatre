package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.biz.Reservation;
import com.jpmc.theater.biz.Showing;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;

public class DiscountTests {
    @Test
    void specialMovieWith20PercentDiscount() {
    	
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        Reservation book = new Reservation(new Customer("Bhakti","123"),showing,3);
        assertEquals(30, book.getTotalFee());
    }
    
    @Test
    void timeSlotSpecificDiscount() {
    	
    	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
    	LocalDate date = LocalDate.now();
    	LocalTime time = LocalTime.of(12, 34);	
    	LocalDateTime localDateTime = LocalDateTime.of(date, time);
    	Showing showing = new Showing(spiderMan, 5, localDateTime);
    	Reservation book = new Reservation(new Customer("Bhakti","123"),showing,2);
    	assertEquals(18.75, book.getTotalFee());
    }
    
    
}
