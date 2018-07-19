
package com.chandan.owesum.payment.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "paymentBeneficiaryId",
    "beneficiaryPartyId",
    "benefittedAmount",
    "percentageReference"
})
public class PaymentBeneficiaryDto implements Serializable
{

    @JsonProperty("paymentBeneficiaryId")
    private String paymentBeneficiaryId;
    @JsonProperty("beneficiaryPartyId")
    private String beneficiaryPartyId;
    @JsonProperty("benefittedAmount")
    private BigDecimal benefittedAmount;
    @JsonProperty("percentageReference")
    private BigDecimal percentageReference;

    
    
    private final static long serialVersionUID = 2611285579896114048L;

    @JsonProperty("paymentBeneficiaryId")
    public String getPaymentBeneficiaryId() {
        return paymentBeneficiaryId;
    }

    @JsonProperty("paymentBeneficiaryId")
    public void setPaymentBeneficiaryId(String paymentBeneficiaryId) {
        this.paymentBeneficiaryId = paymentBeneficiaryId;
    }

    @JsonProperty("beneficiaryPartyId")
    public String getBeneficiaryPartyId() {
        return beneficiaryPartyId;
    }

    @JsonProperty("beneficiaryPartyId")
    public void setBeneficiaryPartyId(String beneficiaryPartyId) {
        this.beneficiaryPartyId = beneficiaryPartyId;
    }

    @JsonProperty("benefittedAmount")
    public BigDecimal getBenefittedAmount() {
        return benefittedAmount;
    }

    @JsonProperty("benefittedAmount")
    public void setBenefittedAmount(BigDecimal benefittedAmount) {
        this.benefittedAmount = benefittedAmount;
    }

    @JsonProperty("percentageReference")
    public BigDecimal getPercentageReference() {
        return percentageReference;
    }

    @JsonProperty("percentageReference")
    public void setPercentageReference(BigDecimal percentageReference) {
        this.percentageReference = percentageReference;
    }

}
