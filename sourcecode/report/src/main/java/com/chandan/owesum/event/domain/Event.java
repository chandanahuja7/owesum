package com.chandan.owesum.event.domain;

import java.util.LinkedList;
import java.util.List;

import com.chandan.owesum.party.domain.Party;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

	private String eventId;
	private String eventName;
	private List<Party>  parties  = new LinkedList();
	
	
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public List<Party> getParties() {
		return parties;
	}
	public void setParties(List<Party> parties) {
		this.parties = parties;
	}
	
	
	
	
	
}
