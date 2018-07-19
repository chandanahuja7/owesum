package com.chandan.owesum.payment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chandan.owesum.payment.domain.Payment;
import com.chandan.owesum.payment.domain.PaymentBeneficiary;
import com.chandan.owesum.util.KeyGenerator;

@Repository
public class PaymentDaoImpl implements PaymentDao {

	private final String INSERT_PAYMENT_SQL = " INSERT INTO public.payment  ( payment_id, 	event_id, purpose_tp_cd, description, paidby_party_id, payment_dt, payment_amount)	VALUES (?, ?, ?, ?, ?, ?, ?) ";
	private final String INSERT_PAYMENT_BENEFICIARY_SQL = " INSERT INTO public.paymentbeneficiary(payment_id, beneficiary_party_id, benefitted_amount, percent_reference ) VALUES (?, ?, ?, ?) ";

	private final String DELETE_PAYMENT_SQL = " DELETE FROM public.payment WHERE payment_id = ? ";
	
	private final String UPDATE_PAYMENT_SQL =  "UPDATE public.payment SET event_id=?, purpose_tp_cd=?, description=?, paidby_party_id=?, payment_dt=?, payment_amount=?  WHERE payment_id = ? "; 
	
	private final String SELECT_PAYMENT_SQL = ""
			+ "select "
			+ "	pm.payment_id pm_payment_id "
			+ "	, pm.event_id 	pm_event_id "
			+ "	, pm.purpose_tp_cd pm_purpose_tp_cd "
			+ "	, pm.description pm_description "
			+ "	, pm.paidby_party_id pm_paidby_party_id "
			+ "	, pm.payment_dt pm_payment_dt "
			+ "	, pm.payment_amount pm_payment_amount "
			+ "	, pb.paymentbeneficiary_id  pb_paymentbeneficiary_id "
			+ "	, pb.beneficiary_party_id  pb_beneficiary_party_id "
			+ "	, pb.benefitted_amount  pb_benefitted_amount "
			+ "	, pb.percent_reference  pb_percent_reference "
			+ "	, pb.payment_id  pb_payment_id "
			+ "	from "
			+ "	payment pm "
			+ "	join paymentbeneficiary pb on pm.payment_id = pb.payment_id "
			+ "	where pm.payment_id =  ? ";
	
		
	
	
	

	private final String PAYMENT_ID_SUFFIX = "PA_";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PaymentBeneficiaryDao paymentBeneficiaryDao;

	@Override
	@Transactional
	public void addPayment(Payment payment) {

		String paymentId = PAYMENT_ID_SUFFIX + KeyGenerator.generateKey();

		jdbcTemplate.update(INSERT_PAYMENT_SQL, paymentId, payment.getEventId(), payment.getPurposeTpCd(),
				payment.getDescription(), payment.getPaidByPartyId(), payment.getPaymentDate(),
				payment.getPaymentAmount());
		
		payment.setPaymentId(paymentId);
		

		payment.getPaymentBeneficiaries().forEach(paymentBeneficiary -> {
			paymentBeneficiary.setPaymentId(paymentId);
			addPaymentBeneficiary(paymentBeneficiary);
		});

	}

	private void addPaymentBeneficiary(PaymentBeneficiary paymentBeneficiary) {

		paymentBeneficiaryDao.addPaymentBeneficiary(paymentBeneficiary);
	}

	
	
	@Override
	@Transactional
	public void updatePayment(Payment payment)
	{
		
		
		
		
		// check if it it exists
		// if yes
			//update it and rest all of payment beneficiary details
			// also check if any exist in db but not in incoming
		// else
			//insert it and rest all of the payment beneficiary details
			
				
		
		
// "UPDATE public.payment SET event_id=?, purpose_tp_cd=?, description=?, paidby_party_id=?, payment_dt=?, payment_amount=?  WHERE payment_id = ? ";		
		jdbcTemplate.update(UPDATE_PAYMENT_SQL
				, payment.getEventId()
				, payment.getPurposeTpCd()
				, payment.getDescription()
				,payment.getPaidByPartyId()	
				,payment.getPaymentDate()
				, payment.getPaymentAmount()
				, payment.getPaymentId()
				);
		
		
		
		
	}
	
	
	@Override
	@Transactional
	public void deletePayment(String paymenId)
	{
		jdbcTemplate.update(DELETE_PAYMENT_SQL
				,paymenId			
				);
	}
	
	
	
	@Override
	@Transactional
	public Payment getById(String paymentId)
	{
		List<Payment> payments =   jdbcTemplate.query(SELECT_PAYMENT_SQL, new Object[] { paymentId },	new PaymentExtractor());

		if(payments  != null && !payments.isEmpty())
		{
			return payments.get(0);
		}
		
		return null;
	}
	
	
}



