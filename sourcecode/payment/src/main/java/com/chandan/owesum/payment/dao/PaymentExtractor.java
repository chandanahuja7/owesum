package com.chandan.owesum.payment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.chandan.owesum.payment.domain.Payment;
import com.chandan.owesum.payment.domain.PaymentBeneficiary;

public class PaymentExtractor implements ResultSetExtractor<List<Payment>> {

	private Map<String, Payment> paymentMap = new HashMap();
	private Map<String, PaymentBeneficiary> paymentBeneficiaryMap = new HashMap();

	@Override
	public List<Payment> extractData(ResultSet rs) throws DataAccessException, SQLException {

		List<Payment> payments = new LinkedList();

		while (rs.next()) {

			Payment payment = null;
			PaymentBeneficiary paymentBeneficiary = null;

			{
				// Payment
				String paymentId = rs.getString("pm_payment_id");
				Object objPayment = paymentMap.get(paymentId);
				if (objPayment == null) {
					payment = buildPayment(rs);
					paymentMap.put(paymentId, payment);

				} else {
					payment = (Payment) objPayment;
				}
			}
			{
				// PaymentBeneficiary
				String paymentBeneficiaryId = rs.getString("pb_paymentbeneficiary_id");
				Object objPaymentBeneficiary = paymentBeneficiaryMap.get(paymentBeneficiaryId);
				if (objPaymentBeneficiary == null) {
					paymentBeneficiary = buildPaymentBeneficiary(rs);
					paymentBeneficiaryMap.put(paymentBeneficiaryId, paymentBeneficiary);
					payment.getPaymentBeneficiaries().add(paymentBeneficiary);

				} else {
					paymentBeneficiary = (PaymentBeneficiary) objPaymentBeneficiary;
				}

			}
		}

		payments.addAll(paymentMap.values());
		return payments;

	}

	private Payment buildPayment(ResultSet rs) throws SQLException {
		Payment payment = new Payment();
		payment.setDescription(rs.getString("pm_description"));
		payment.setEventId(rs.getString("pm_event_id"));
		payment.setPaidByPartyId(rs.getString("pm_paidby_party_id"));
		payment.setPaymentAmount(rs.getBigDecimal("pm_payment_amount"));
		payment.setPaymentDate(rs.getDate("pm_payment_dt").toLocalDate());
		payment.setPaymentId(rs.getString("pm_payment_id"));
		payment.setPurposeTpCd(rs.getInt("pm_purpose_tp_cd"));

		return payment;
	}

	private PaymentBeneficiary buildPaymentBeneficiary(ResultSet rs) throws SQLException {
		PaymentBeneficiaryMapper paymentBeneficiaryMapper = new PaymentBeneficiaryMapper();
		return paymentBeneficiaryMapper.buildPaymentBeneficiary(rs);
	}

}
