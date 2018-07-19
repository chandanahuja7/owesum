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

		System.out.println(" payments JJJJJJJJJJJJJJJJJJBBBBBBBBBBBBBBBJJJJJJJJJJJJJJ");
		Payment payment = PaymentAdapter.getPaymentEntity(paymentMessageDto.getPayment());
		paymentService.addPayment(payment);

		LOGGER.info("Enter to New Price Calculator Controller Class JSON");
		return new ResponseEntity<>(paymentMessageDto, HttpStatus.OK);

	}

	@RequestMapping(value = "/payments1", method = RequestMethod.POST, headers = {
			"Accept=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addPayment1(HttpServletRequest request, @Valid @RequestBody String paymentDto) {

		LOGGER.info("Enter to New Price Calculator Controller Class JSON");
		System.out.println(" payments1 JJJJJJJJJJJJJJJJJJBBBBBBBBBBBBBBBJJJJJJJJJJJJJJ");
		// Payment payment = PaymentAdadter.getPaymentEntity(paymentDto);
		// paymentService.addPayment(payment);

		return new ResponseEntity<>(paymentDto, HttpStatus.OK);

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

	
	
	
	/*
	 * @RequestMapping(value = "/pricecalculator/price.json", method =
	 * RequestMethod.POST, headers = { "Accept=application/json" }, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Consignment>
	 * getNewPriceJSON(HttpServletRequest request , @Valid @RequestBody Consignment
	 * consignment) {
	 * 
	 * 
	 * //PriceRequest pr = new PriceRequest(request , consignment);
	 * 
	 * 
	 * LOGGER.info("Enter to New Price Calculator Controller Class JSON");
	 * 
	 * // adapter layer for converting raw XML file to transformed XML consignment =
	 * priceService.calculatePrice(consignment);
	 * 
	 * LOGGER.info("Price calculated for Consignment");
	 * 
	 * //PriceLogDto priceLogDto = new PriceLogDto(request , consignment);
	 * 
	 * 
	 * return new ResponseEntity<>(consignment, HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/pricecalculator/price.xml", method =
	 * RequestMethod.POST, headers = { "Accept=application/xml" }, produces =
	 * MediaType.APPLICATION_XML_VALUE) public ResponseEntity<Consignment>
	 * getNewPriceXML(HttpServletRequest request , @Valid @RequestBody Consignment
	 * consignment) {
	 * 
	 * //PriceRequest pr = new PriceRequest(request , consignment);
	 * 
	 * LOGGER.info("Enter to New Price Calculator Controller Class XML"); //
	 * consignment.setRequestFromLM(false); // adapter layer for converting raw XML
	 * file to transformed XML consignment =
	 * priceService.calculatePrice(consignment);
	 * 
	 * LOGGER.info("Price calculated for Consignment");
	 * 
	 * //PriceLogDto priceLogDto = new PriceLogDto(request , consignment);
	 * 
	 * 
	 * return new ResponseEntity<>(consignment, HttpStatus.OK); }
	 * 
	 * 
	 * 
	 * @RequestMapping(value = "/pricecalculator/pricebatch.xml", method =
	 * RequestMethod.POST, headers = { "Accept=application/xml" }, produces =
	 * MediaType.APPLICATION_XML_VALUE) public ResponseEntity<ConsignmentList>
	 * getNewPriceBatchXML(HttpServletRequest request , @Valid @RequestBody
	 * ConsignmentList consignmentList) {
	 * 
	 * //PriceRequest pr = PrceiRequestBuilder.build(consignmentList);
	 * 
	 * //PriceRequest pr = new PriceRequest(request , consignmentList);
	 * 
	 * 
	 * LOGGER.info("Enter to New Price Calculator New Batch Controller Class XML");
	 * for(Consignment consignment : consignmentList.getConsignments() ) {
	 * consignment = priceService.calculatePrice(consignment); }
	 * LOGGER.info("Price calculated for all Consignments");
	 * 
	 * PriceLogDto priceLogDto = null; if(consignmentList.ConsignmentListHeader !=
	 * null && consignmentList.ConsignmentListHeader.getSystemCode().equals("LMPE")
	 * ) { priceLogDto = new PriceLogDto(request , consignmentList ,
	 * PriceLogDto.REQ_CHANNEL_LMNEW); }
	 * 
	 * 
	 * 
	 * return new ResponseEntity<>(consignmentList, HttpStatus.OK); }
	 * 
	 * 
	 * @RequestMapping(value = "/pricecalculator/oldprice", method =
	 * RequestMethod.POST, headers = { "Accept=application/xml" }, consumes = {
	 * MediaType.APPLICATION_XML_VALUE }, produces = {
	 * MediaType.APPLICATION_XML_VALUE }) public ResponseEntity<ConsignmentList>
	 * getOldPrice(HttpServletRequest request , @Valid @RequestBody LM6119Message
	 * lMDataModel) {
	 * 
	 * LOGGER.
	 * debug(" Price Request entered in getOldPrice for Old Srrvice , LM Interface "
	 * );
	 * 
	 * List<Consignment> cl =
	 * priceService.calculatePrice(lMDataModel.getlMOrderLineList());
	 * ConsignmentList consignmentList = new ConsignmentList();
	 * consignmentList.setConsignments(cl);
	 * consignmentList.ConsignmentListRequestDetails =
	 * lMDataModel.ConsignmentListRequestDetails;
	 * consignmentList.ConsignmentListHeader = lMDataModel.ConsignmentListHeader ;
	 * 
	 * XmlMapper xmlMapper = new XmlMapper();
	 * 
	 * PriceLogDto priceLogDto = null; if(consignmentList != null &&
	 * consignmentList.ConsignmentListHeader != null) priceLogDto = new
	 * PriceLogDto(request , consignmentList , PriceLogDto.REQ_CHANNEL_LMOLD );
	 * 
	 * 
	 * return new ResponseEntity<>(consignmentList, HttpStatus.OK); }
	 * 
	 * 
	 * @RequestMapping(value="/pricecalculator/listofzonesandprices", method =
	 * RequestMethod.POST, headers = { "Accept=application/json" }, consumes = {
	 * MediaType.APPLICATION_JSON_VALUE }, produces = {
	 * MediaType.APPLICATION_JSON_VALUE }) public
	 * ResponseEntity<ShippingGuideResponse>
	 * getListOfZonesAndPrices(HttpServletRequest request, @Valid @RequestBody
	 * ShippingGuideRequest shippingGuideRequest){
	 * 
	 * ShippingGuideResponse shippingGuideResponse =
	 * priceService.getListOfZonesAndPrices(shippingGuideRequest);
	 * 
	 * return new ResponseEntity<>(shippingGuideResponse, HttpStatus.OK); }
	 */

}
