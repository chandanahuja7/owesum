package com.chandan.owesum.payment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chandan.owesum.payment.domain.PaymentBeneficiary;
import com.chandan.owesum.util.KeyGenerator;


@Repository
public class PaymentBeneficiaryDaoImpl implements PaymentBeneficiaryDao
{

	
	 private final String INSERT_PAYMENT_BENEFICIARY_SQL = " INSERT INTO public.paymentbeneficiary(paymentbeneficiary_id, payment_id, beneficiary_party_id, benefitted_amount, percent_reference ) VALUES (?, ?, ?, ?, ?) " ;
	 private final String DELETE_PAYMENT_BENEFICIARY_SQL = " DELETE FROM public.paymentbeneficiary WHERE paymentbeneficiary_id = ? " ;  
	 private final String UPDATE_PAYMENT_BENEFICIARY_SQL = " UPDATE public.paymentbeneficiary SET beneficiary_party_id=?, benefitted_amount=?, percent_reference=?, payment_id=? WHERE paymentbeneficiary_id = ? " ;
	 private final String SELECT_PAYMENT_BENEFICIARY_SQL = ""
			 + "select "
			 + "	 pb.paymentbeneficiary_id  pb_paymentbeneficiary_id "
			 + "	, pb.beneficiary_party_id  pb_beneficiary_party_id "
			 + "	, pb.benefitted_amount  pb_benefitted_amount "
			 + "	, pb.percent_reference  pb_percent_reference "
			 + "	, pb.payment_id  pb_payment_id "
			 + "	from "
			 + "	 paymentbeneficiary pb where pb.paymentbeneficiary_id = ?";
	 
	 
	 private final String PAYMENTBENEFICIARY_ID_SUFFIX = "PB_" ;

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	/*@Autowired
	private IPriceRequestDAO priceRequestDAO;*/
	
	
	@Override
	public void addPaymentBeneficiary(PaymentBeneficiary paymentBeneficiary)
    {

		String paymentBeneficiaryId = PAYMENTBENEFICIARY_ID_SUFFIX +  KeyGenerator.generateKey();

		jdbcTemplate.update(INSERT_PAYMENT_BENEFICIARY_SQL
				,paymentBeneficiaryId
				,paymentBeneficiary.getPaymentId()
				, paymentBeneficiary.getBeneficiaryPartyId()
				, paymentBeneficiary.getBenefittedAmount()
				, paymentBeneficiary.getPercentageReference()
				);
		paymentBeneficiary.setPaymentBeneficiaryId(paymentBeneficiaryId);
				
    }
	
	
	
	
	@Override
	public void updatePaymentBeneficiary(PaymentBeneficiary paymentBeneficiary)
	{
		
		jdbcTemplate.update(UPDATE_PAYMENT_BENEFICIARY_SQL
				, paymentBeneficiary.getBeneficiaryPartyId()
				, paymentBeneficiary.getBenefittedAmount()
				, paymentBeneficiary.getPercentageReference()
				,paymentBeneficiary.getPaymentId()		
				,paymentBeneficiary.getPaymentBeneficiaryId()
				);
	}
    
	
	@Override
    public void deletePaymentBeneficiary(String paymentBeneficiaryId)
    {
		jdbcTemplate.update(DELETE_PAYMENT_BENEFICIARY_SQL
				,paymentBeneficiaryId			
				);
    }
    
	
	@Override
	public PaymentBeneficiary getById(String paymentBeneficiaryId)
	{
		List<PaymentBeneficiary> paymentBeneficiaries = jdbcTemplate.query(SELECT_PAYMENT_BENEFICIARY_SQL, 
                new PaymentBeneficiaryMapper());
		
		if(paymentBeneficiaries != null && !paymentBeneficiaries.isEmpty())
		{
			return paymentBeneficiaries.get(0);
		}
		
		return null;
			
	}
	
	
	

}

