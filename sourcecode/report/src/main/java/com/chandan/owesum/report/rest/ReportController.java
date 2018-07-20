package com.chandan.owesum.report.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chandan.owesum.report.domain.OweSumEventReport;
import com.chandan.owesum.report.service.ReportService;

@RestController
public class ReportController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private ReportService reportService;

	@Autowired
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	
	@RequestMapping(value = "/reports/{eventId}", method = RequestMethod.GET, headers = {
	"Accept=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OweSumEventReport> retrieveStudent(@PathVariable String eventId) throws Exception{
		
		OweSumEventReport oweSumEventReport = reportService.getReport(eventId);

		if (oweSumEventReport == null)
			throw new Exception("id-" + eventId);

	
		return new ResponseEntity<>(oweSumEventReport, HttpStatus.OK);
	}

	
}
