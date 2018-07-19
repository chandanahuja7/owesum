
package com.chandan.owesum.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Payment 
{

    private String paymentId;
    private String eventId;
    private Integer purposeTpCd;
    private String description;
    private String paidByPartyId;
    private LocalDate paymentDate;
    private BigDecimal paymentAmount;
    private List<PaymentBeneficiary> paymentBeneficiaries = new ArrayList<PaymentBeneficiary>();
    
    
    private final static long serialVersionUID = -7388100988606835474L;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Integer getPurposeTpCd() {
        return purposeTpCd;
    }

    public void setPurposeTpCd(Integer purposeTpCd) {
        this.purposeTpCd = purposeTpCd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaidByPartyId() {
        return paidByPartyId;
    }

    public void setPaidByPartyId(String paidByPartyId) {
        this.paidByPartyId = paidByPartyId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public List<PaymentBeneficiary> getPaymentBeneficiaries() {
        return paymentBeneficiaries;
    }

    public void setPaymentBeneficiaries(List<PaymentBeneficiary> paymentBeneficiaries) {
        this.paymentBeneficiaries = paymentBeneficiaries;
    }






}
