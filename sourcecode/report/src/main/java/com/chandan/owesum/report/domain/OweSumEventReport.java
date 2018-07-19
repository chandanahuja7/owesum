package com.chandan.owesum.report.domain;

import java.util.HashSet;
import java.util.Set;

import com.chandan.owesum.event.domain.Event;
import com.chandan.owesum.payment.domain.Payment;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OweSumEventReport {

	private Event event;
	private Set<OweSumDetails> owesumDetailsSet ;
	private Set<Payment> suggestedPaymentsSet = new HashSet();
	
	
	
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Set<OweSumDetails> getOwesumDetailsSet() {
		return owesumDetailsSet;
	}
	public void setOwesumDetailsSet(Set<OweSumDetails> owesumDetailsSet) {
		this.owesumDetailsSet = owesumDetailsSet;
	}
	public Set<Payment> getSuggestedPaymentsSet() {
		return suggestedPaymentsSet;
	}
	public void setSuggestedPaymentsSet(Set<Payment> suggestedPaymentsSet) {
		this.suggestedPaymentsSet = suggestedPaymentsSet;
	}
	
	
	
	
}
