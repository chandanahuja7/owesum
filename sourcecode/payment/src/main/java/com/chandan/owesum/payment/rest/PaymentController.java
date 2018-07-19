package com.chandan.owesum.payment.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chandan.owesum.payment.adapter.PaymentAdapter;
import com.chandan.owesum.payment.domain.Payment;
import com.chandan.owesum.payment.dto.PaymentDto;
import com.chandan.owesum.payment.dto.PaymentMessageDto;
import com.chandan.owesum.payment.service.PaymentService;

@RestController
public class PaymentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@RequestMapping(value = "/payments", method = RequestMethod.POST, headers = {
			"Accept=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentMessageDto> addPayment(HttpServletRequest request,
			@Valid @RequestBody PaymentMessageDto paymentMessageDto) {

		Payment payment = PaymentAdapter.getPaymentEntity(paymentMessageDto.getPayment());
		paymentService.addPayment(payment);

		return new ResponseEntity<>(paymentMessageDto, HttpStatus.OK);

	}

	

	
	@RequestMapping(value = "/payments/{paymentId}", method = RequestMethod.GET, headers = {
	"Accept=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentMessageDto> retrieveStudent(@PathVariable String paymentId) throws Exception{
		
		Payment payment = paymentService.getPayment(paymentId);

		if (payment == null)
			//throw new StudentNotFoundException("id-" + id);
			throw new Exception("id-" + paymentId);

		PaymentMessageDto paymentMessageDto = PaymentAdapter.getPaymentDto(payment);

		
		return new ResponseEntity<>(paymentMessageDto, HttpStatus.OK);
	}

	
}
