package com.chandan.owesum.report.adapter;

import java.util.LinkedList;
import java.util.List;

import com.chandan.owesum.payment.domain.Payment;
import com.chandan.owesum.payment.domain.PaymentBeneficiary;
import com.chandan.owesum.payment.dto.PaymentBeneficiaryDto;
import com.chandan.owesum.payment.dto.PaymentDto;
import com.chandan.owesum.payment.dto.PaymentMessageDto;
import com.chandan.owesum.report.domain.OweSumEventReport;
import com.chandan.owesum.report.dto.OweSumEventReportDto;

public class OweSumEventReportAdapter {

	
	/*
    public OweSumEventReportDto adapt(OweSumEventReport oweSumEventReport)
	{
    	OweSumEventReportDto oweSumEventReportDto = new OweSumEventReportDto
	}
	
	
	public static PaymentMessageDto getPaymentDto(Payment payment) 
	{
		PaymentDto paymentDto = new PaymentDto();
		PaymentMessageDto paymentMessageDto = new PaymentMessageDto();
		paymentMessageDto.setPayment(paymentDto);
		
		
		paymentDto.setDescription(payment.getDescription());
		paymentDto.setEventId(payment.getEventId());
		paymentDto.setPaidByPartyId(payment.getPaidByPartyId());
		paymentDto.setPaymentAmount(payment.getPaymentAmount());
		paymentDto.setPaymentDate(payment.getPaymentDate());
		paymentDto.setPaymentId(payment.getPaymentId());
		paymentDto.setPurposeTpCd(payment.getPurposeTpCd());
		
	
		if (payment.getPaymentBeneficiaries() != null) {
			
			List<PaymentBeneficiaryDto> paymentBeneficiaries = new LinkedList();			
			paymentDto.setPaymentBeneficiaries(paymentBeneficiaries);
			
			for( PaymentBeneficiary paymentBeneficiary  : payment.getPaymentBeneficiaries())
			{
				PaymentBeneficiaryDto  pbDto = getPaymentBeneficiaryDto(paymentBeneficiary);
				paymentBeneficiaries.add(pbDto);				
			}
			
		}
		


		return paymentMessageDto;
	}
	
	
	private static PaymentBeneficiaryDto getPaymentBeneficiaryDto(   PaymentBeneficiary paymentBeneficiary)
	{
		PaymentBeneficiaryDto  pb =  new PaymentBeneficiaryDto();
		
		pb.setBeneficiaryPartyId(paymentBeneficiary.getBeneficiaryPartyId());
		pb.setBenefittedAmount(paymentBeneficiary.getBenefittedAmount());
		pb.setPaymentBeneficiaryId(paymentBeneficiary.getPaymentBeneficiaryId());
		pb.setPercentageReference(paymentBeneficiary.getPercentageReference());
		return pb;
	}
	
	
	
	
	public static Payment getPaymentEntity(PaymentDto paymentDto) {
		
		
		Payment payment = new Payment();
		
		payment.setDescription(paymentDto.getDescription());
		payment.setEventId(paymentDto.getEventId());
		payment.setPaidByPartyId(paymentDto.getPaidByPartyId());
		payment.setPaymentAmount(paymentDto.getPaymentAmount());
		payment.setPaymentDate(paymentDto.getPaymentDate());
		payment.setPaymentId(paymentDto.getPaymentId());
		payment.setPurposeTpCd(paymentDto.getPurposeTpCd());
		
	
		if (paymentDto.getPaymentBeneficiaries() != null) {
			
			List<PaymentBeneficiary> paymentBeneficiaries = new LinkedList();			
			payment.setPaymentBeneficiaries(paymentBeneficiaries);
			
			for( PaymentBeneficiaryDto paymentBeneficiaryDto  : paymentDto.getPaymentBeneficiaries())
			{
				PaymentBeneficiary  pb = getPaymentBeneficiaryEntity(payment, paymentBeneficiaryDto);
				paymentBeneficiaries.add(pb);				
			}
			
		}

		return payment;
	}

	
	
	private static PaymentBeneficiary getPaymentBeneficiaryEntity( Payment payment,  PaymentBeneficiaryDto paymentBeneficiaryDto)
	{
		PaymentBeneficiary  pb =  new PaymentBeneficiary();
		
		pb.setBeneficiaryPartyId(paymentBeneficiaryDto.getBeneficiaryPartyId());
		pb.setBenefittedAmount(paymentBeneficiaryDto.getBenefittedAmount());
		pb.setPaymentBeneficiaryId(paymentBeneficiaryDto.getPaymentBeneficiaryId());
		pb.setPaymentId(payment.getPaymentId());
		pb.setPercentageReference(paymentBeneficiaryDto.getPercentageReference());
		return pb;
	}*/

}
