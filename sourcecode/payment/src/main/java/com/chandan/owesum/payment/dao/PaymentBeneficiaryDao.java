package com.chandan.owesum.payment.dao;

import com.chandan.owesum.payment.domain.PaymentBeneficiary;

public interface PaymentBeneficiaryDao {

    public void addPaymentBeneficiary(PaymentBeneficiary paymentBeneficiary);
	
	public void updatePaymentBeneficiary(PaymentBeneficiary paymentBeneficiary);
    
    public void deletePaymentBeneficiary(String paymentBeneficiaryId);
    
	public PaymentBeneficiary getById(String paymentBeneficiaryId);    
    
}
