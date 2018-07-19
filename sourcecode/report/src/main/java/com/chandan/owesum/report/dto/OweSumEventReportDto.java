package com.chandan.owesum.report.dto;

import java.util.Set;

import com.chandan.owesum.event.dto.EventDto;
import com.chandan.owesum.payment.dto.PaymentDto;

public class OweSumEventReportDto {

	private EventDto event;
	private Set<OweSumDetailsDto> owesumDetails ;
	private Set<PaymentDto> suggestedPayments ;
	
	
	
	public EventDto getEvent() {
		return event;
	}
	public void setEvent(EventDto event) {
		this.event = event;
	}
	public Set<OweSumDetailsDto> getOwesumDetails() {
		return owesumDetails;
	}
	public void setOwesumDetails(Set<OweSumDetailsDto> owesumDetails) {
		this.owesumDetails = owesumDetails;
	}
	public Set<PaymentDto> getSuggestedPayments() {
		return suggestedPayments;
	}
	public void setSuggestedPayments(Set<PaymentDto> suggestedPayments) {
		this.suggestedPayments = suggestedPayments;
	}
	
	
	
}
