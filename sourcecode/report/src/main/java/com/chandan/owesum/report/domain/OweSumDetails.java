package com.chandan.owesum.report.domain;

import java.math.BigDecimal;

import com.chandan.owesum.party.domain.Party;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OweSumDetails {

	private Party party = new Party();;
	private BigDecimal totalSpent;
	private BigDecimal isOwed;
	private BigDecimal owes;
	private BigDecimal balance;
	
	@JsonIgnore
	public BigDecimal runningBalance;
	
	
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((party == null) ? 0 : party.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OweSumDetails other = (OweSumDetails) obj;
		if (party == null) {
			if (other.party != null)
				return false;
		} else if (!party.equals(other.party))
			return false;
		return true;
	}
	
	
	
	
	
	
}
