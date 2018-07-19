package com.chandan.owesum.payment.service;

import com.chandan.owesum.payment.domain.Payment;

public interface PaymentService {

    public boolean addPayment(Payment payment );
    
    public Payment getPayment(String paymentId );   
	
}
