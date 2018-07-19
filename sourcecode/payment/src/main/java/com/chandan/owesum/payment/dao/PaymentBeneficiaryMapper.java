package com.chandan.owesum.payment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chandan.owesum.payment.domain.PaymentBeneficiary;


public class PaymentBeneficiaryMapper implements RowMapper<PaymentBeneficiary> {
    @Override
    public PaymentBeneficiary mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	return buildPaymentBeneficiary(rs);
    }
    
    
	public PaymentBeneficiary buildPaymentBeneficiary(ResultSet rs) throws SQLException
	{
		PaymentBeneficiary paymentBeneficiary = new PaymentBeneficiary();
    	paymentBeneficiary.setBeneficiaryPartyId(rs.getString("pb_beneficiary_party_id"));
    	paymentBeneficiary.setBenefittedAmount(rs.getBigDecimal("pb_benefitted_amount"));
    	paymentBeneficiary.setPaymentBeneficiaryId(rs.getString("pb_paymentbeneficiary_id"));
    	paymentBeneficiary.setPaymentId(rs.getString("pb_payment_id"));
    	paymentBeneficiary.setPercentageReference(rs.getBigDecimal("pb_percent_reference"));
    	return paymentBeneficiary;
	}

    

}
