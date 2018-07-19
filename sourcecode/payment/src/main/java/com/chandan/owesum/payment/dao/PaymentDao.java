package com.chandan.owesum.payment.dao;

import com.chandan.owesum.payment.domain.Payment;

public interface PaymentDao {

    public void addPayment(Payment payment);
    
	public void updatePayment(Payment payment);
	
	public void deletePayment(String paymenId);
	
	public Payment getById(String payment);
	
}
