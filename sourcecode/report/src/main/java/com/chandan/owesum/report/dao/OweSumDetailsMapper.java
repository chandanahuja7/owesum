package com.chandan.owesum.report.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chandan.owesum.payment.domain.PaymentBeneficiary;
import com.chandan.owesum.report.domain.OweSumDetails;


public class OweSumDetailsMapper implements RowMapper<OweSumDetails> {
    @Override
    public OweSumDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	return buildOweSumDetails(rs);
    }
    
    
	public OweSumDetails buildOweSumDetails(ResultSet rs) throws SQLException
	{
		OweSumDetails oweSumDetails = new OweSumDetails();
		oweSumDetails.setBalance(rs.getBigDecimal("balance"));
		oweSumDetails.setIsOwed(rs.getBigDecimal("isowed"));
		oweSumDetails.setOwes(rs.getBigDecimal("owes"));
		oweSumDetails.setTotalSpent(rs.getBigDecimal("totalspent"));
		oweSumDetails.getParty().setPartyId(rs.getString("party"));
		
    	return oweSumDetails;
	}

    

}
