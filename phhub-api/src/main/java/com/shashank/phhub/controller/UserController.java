package com.shashank.phhub.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shashank.phhub.producer.user.UserEmailProducer;

@RestController
@RequestMapping(value = "/api/ph/p")
public class UserController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserEmailProducer userEmailProducer;

	@RequestMapping(value = "/send-msg/{msg}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> sendMessageViaKafka(@PathVariable(value = "msg") @NotNull String msg) {
		try {
			// This will return all books from the database

			userEmailProducer.sendMessageToQueue(msg);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception ex) {
			String errorMessage;
			errorMessage = ex + " <== error";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
