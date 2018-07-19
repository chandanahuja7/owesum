
package com.chandan.owesum.payment.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentBeneficiary 
{

	
    private String paymentBeneficiaryId;
    private String paymentId;

    private String beneficiaryPartyId;
    private BigDecimal benefittedAmount;
    private BigDecimal percentageReference;
 

    public String getPaymentBeneficiaryId() {
        return paymentBeneficiaryId;
    }

    public void setPaymentBeneficiaryId(String paymentBeneficiaryId) {
        this.paymentBeneficiaryId = paymentBeneficiaryId;
    }

    public String getBeneficiaryPartyId() {
        return beneficiaryPartyId;
    }

    public void setBeneficiaryPartyId(String beneficiaryPartyId) {
        this.beneficiaryPartyId = beneficiaryPartyId;
    }

    public BigDecimal getBenefittedAmount() {
        return benefittedAmount;
    }

    public void setBenefittedAmount(BigDecimal benefittedAmount) {
        this.benefittedAmount = benefittedAmount;
    }

    public BigDecimal getPercentageReference() {
        return percentageReference;
    }

    public void setPercentageReference(BigDecimal percentageReference) {
        this.percentageReference = percentageReference;
    }

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
    
    
    
    

}
