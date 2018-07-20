package com.chandan.owesum.report.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chandan.owesum.payment.domain.Payment;
import com.chandan.owesum.report.dao.OweSumEventReportDao;
import com.chandan.owesum.report.domain.OweSumEventReport;
import com.chandan.owesum.report.engine.PaymentRecommender;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private OweSumEventReportDao oweSumEventReportDao;

	

	
	@Override
    public OweSumEventReport getReport(String eventId )
	{
		OweSumEventReport oweSumEventReport = oweSumEventReportDao.getReport(eventId);
		
		
		// TODO : add logic for Debt-closure Payment suggestion  
		
		Set oweSumDetailsSet  = oweSumEventReport.getOwesumDetailsSet();
		
		Set<Payment> suggestedPayments = PaymentRecommender.buildPaymentRecommendations(oweSumDetailsSet) ;
		
		oweSumEventReport.setSuggestedPaymentsSet(suggestedPayments);		
		
		return oweSumEventReport;
	}

	
	
	
}
