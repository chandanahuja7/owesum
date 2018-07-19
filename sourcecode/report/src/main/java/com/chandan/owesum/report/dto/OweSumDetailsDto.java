package com.chandan.owesum.report.dto;

import java.math.BigDecimal;

import com.chandan.owesum.party.domain.Party;

public class OweSumDetailsDto {

	private Party party = new Party();;
	private BigDecimal totalSpent;
	private BigDecimal isOwed;
	private BigDecimal owes;
	private BigDecimal balance;
	
	
	
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	public BigDecimal getTotalSpent() {
		return totalSpent;
	}
	public void setTotalSpent(BigDecimal totalSpent) {
		this.totalSpent = totalSpent;
	}
	public BigDecimal getIsOwed() {
		return isOwed;
	}
	public void setIsOwed(BigDecimal isOwed) {
		this.isOwed = isOwed;
	}
	public BigDecimal getOwes() {
		return owes;
	}
	public void setOwes(BigDecimal owes) {
		this.owes = owes;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	
	
}
