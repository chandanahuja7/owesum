
package com.chandan.owesum.payment.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "payment"
})
public class PaymentMessageDto implements Serializable
{

    @JsonProperty("payment")
    private PaymentDto payment;

    
    private final static long serialVersionUID = -8813001889694077841L;

    @JsonProperty("payment")
    public PaymentDto getPayment() {
        return payment;
    }

    @JsonProperty("payment")
    public void setPayment(PaymentDto payment) {
        this.payment = payment;
    }

  
}
