
package com.chandan.owesum.payment.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.chandan.owesum.payment.domain.PaymentBeneficiary;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "paymentId", "eventId", "purposeTpCd", "description", "paidByPartyId", "paymentDate",
		"paymentAmount", "paymentBeneficiaries" })
@XmlSeeAlso(PaymentBeneficiary.class)
/*@XmlSeeAlso(ConsignmentList.class)
@XmlType(name = "", propOrder = { "lopenummer", "consignmentNumber", "serviceId", "oebsItemNumber", "pprConversionId", "customerId",
		"orderDate", "fromPostalCode", "toPostalCode", "numberOfPackages", "insuranceAmount", "pricingLevel", "computedFreightPrice",
		"consignmentItems", "additionalServices", "computedWeightDetails", "computedVolumeOfConsignment",
		"computedNumberOfZones", "vatDetails" })
@XmlRootElement(name = "Consignment")
*/
public class PaymentDto implements Serializable {

	private String paymentId;

	private String eventId;

	private Integer purposeTpCd;

	private String description;

	private String paidByPartyId;

	private LocalDate paymentDate;

	private BigDecimal paymentAmount;

	private List<PaymentBeneficiaryDto> paymentBeneficiaries = new ArrayList<PaymentBeneficiaryDto>();

	private final static long serialVersionUID = -7388100988606835474L;

	@XmlElement(name = "paymentId")
	@JacksonXmlProperty(localName = "paymentId")
	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	@XmlElement(name = "eventId")
	@JacksonXmlProperty(localName = "eventId")
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@XmlElement(name = "purposeTpCd")
	@JacksonXmlProperty(localName = "purposeTpCd")
	public Integer getPurposeTpCd() {
		return purposeTpCd;
	}

	public void setPurposeTpCd(Integer purposeTpCd) {
		this.purposeTpCd = purposeTpCd;
	}

	@XmlElement(name = "description")
	@JacksonXmlProperty(localName = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "paidByPartyId")
	@JacksonXmlProperty(localName = "paidByPartyId")
	public String getPaidByPartyId() {
		return paidByPartyId;
	}

	public void setPaidByPartyId(String paidByPartyId) {
		this.paidByPartyId = paidByPartyId;
	}

	@XmlElement(name = "paymentDate")
	@JacksonXmlProperty(localName = "paymentDate")
	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	@XmlElement(name = "paymentAmount")
	@JacksonXmlProperty(localName = "paymentAmount")
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}


	public List<PaymentBeneficiaryDto> getPaymentBeneficiaries() {
		return paymentBeneficiaries;
	}

	public void setPaymentBeneficiaries(List<PaymentBeneficiaryDto> paymentBeneficiaries) {
		this.paymentBeneficiaries = paymentBeneficiaries;
	}

}
