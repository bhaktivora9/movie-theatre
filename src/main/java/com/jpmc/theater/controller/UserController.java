package com.jpmc.theater.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.theater.biz.Reservation;
import com.jpmc.theater.biz.Theater;
import com.jpmc.theater.model.ReserveRequest;

@RestController
public class UserController {

	@Autowired
	Theater theater;

	@GetMapping("/")
	public String index() {
		return "Hey Theatre is up and working!<br><br><br><a href=\"/get/schedule/text\">View Schedule As Text</a>\r\n"
				+ "&emsp;<a href=\"/get/schedule/json\">View Schedule As JSON</a>";
	}

	@GetMapping("/get/schedule/text")
	public String getScheduleAsText() {

		return theater.getScheduleAsText();
	}

	@GetMapping("/get/schedule/json")
	public ResponseEntity<Object> getScheduleAsJson() {
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(theater.getScheduleAsJSON(), HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping("/reserve/{movieId}")
	public ResponseEntity<Object> reserve(@RequestBody ReserveRequest reserveRequest, @PathVariable int movieId) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		Reservation reserve;
		try {

			reserve = theater.reserve(reserveRequest.getCustomer(), movieId, reserveRequest.getHowManyTickets());
			LocalDateTime startTime = reserve.getShowing().getStartTime();
			String response = reserve.getCustomer().getName() + " " + reserve.getAudienceCount()
					+ " tickets booked for " + reserve.getShowing().getMovie().getTitle() + " on "
					+ startTime.format(DateTimeFormatter.ISO_DATE) + " at "
					+ startTime.format(DateTimeFormatter.ISO_TIME) + " .Price before discount = "
					+ reserve.getFeeBeforeDiscount() + ". After Discount total fee = " + reserve.getTotalFee();
			Map<String, String> map = Map.of("response", response);
			ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(map, httpHeaders, HttpStatus.OK);
			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
			// On failure different entity or handle exception;
			ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
	}

}
