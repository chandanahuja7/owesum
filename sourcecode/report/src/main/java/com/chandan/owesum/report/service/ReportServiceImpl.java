package com.chandan.owesum.report.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chandan.owesum.party.domain.Party;
import com.chandan.owesum.payment.domain.Payment;
import com.chandan.owesum.payment.domain.PaymentBeneficiary;
import com.chandan.owesum.report.dao.OweSumEventReportDao;
import com.chandan.owesum.report.domain.OweSumDetails;
import com.chandan.owesum.report.domain.OweSumEventReport;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private OweSumEventReportDao oweSumEventReportDao;

	
	Payment buildSuggestedPayment(Party payer, Party payee, BigDecimal amount)
	{
		Payment payment = new Payment();

		
		payment.setPaidByPartyId(payer.getPartyId());
		payment.setPaymentAmount(amount);
		payment.setPurposeTpCd(7777);
		
		PaymentBeneficiary pb = new PaymentBeneficiary();
		pb.setBeneficiaryPartyId(payee.getPartyId());
		pb.setBenefittedAmount(amount);
		payment.getPaymentBeneficiaries().add(pb);		
		return payment;
	}
	
	
	
	private Set<Payment> buildPaymentSuggestions(Set<OweSumDetails> oweSumDetailsSet) 
	{
		
		
		Comparator<OweSumDetails> creditorsComparator = (OweSumDetails o1, OweSumDetails o2) -> (o1.getBalance().compareTo(o2.getBalance()));
		Comparator<OweSumDetails> debtorsComparator = (OweSumDetails o1, OweSumDetails o2) -> ( o2.getBalance().compareTo(o1.getBalance()));

		TreeSet<OweSumDetails> creditorsSet = new TreeSet(creditorsComparator);
		TreeSet<OweSumDetails> debtorsSet = new TreeSet(debtorsComparator);
		
		// Segregate in debtors and creditors
		oweSumDetailsSet.forEach((OweSumDetails oweSumDetails) -> { if(oweSumDetails.getBalance().compareTo(BigDecimal.ZERO) > 0 ) creditorsSet.add(oweSumDetails);
		if( BigDecimal.ZERO.compareTo(oweSumDetails.getBalance()) > 0 ) debtorsSet.add(oweSumDetails); 
		}   );


		Comparator<Payment> paymentComparator = (Payment p1, Payment p2) ->    p2.getPaymentAmount().compareTo(p1.getPaymentAmount()             );
		Set<Payment> paymentSet = new TreeSet(paymentComparator);
		
		
		
		for(OweSumDetails debtorOweSumDetails   :debtorsSet)
		{
			
			if(debtorOweSumDetails.runningBalance == null)
			{
				// This is Negative value hence negated
				debtorOweSumDetails.runningBalance = new BigDecimal(debtorOweSumDetails.getBalance().toString()).negate();
			}
			


			for(OweSumDetails creditorOweSumDetails :creditorsSet)
			{
				if(creditorOweSumDetails.runningBalance == null)
				{
					creditorOweSumDetails.runningBalance  = new BigDecimal(creditorOweSumDetails.getBalance().toString());			

				}
				
				//Need to make a payment to this creditor only if he owes anything now
				if(creditorOweSumDetails.runningBalance.compareTo(BigDecimal.ZERO) > 0)
				{
					
					
					int compareToValue = debtorOweSumDetails.runningBalance.compareTo(creditorOweSumDetails.runningBalance);
					
					// if debtor value is less than equal to creditor 					
					if(compareToValue <= 0 )
					{
						// pay to current Creditor entire amount which debtor need to pay
						BigDecimal suggestedAmount =  debtorOweSumDetails.runningBalance;
						Payment suggestedPayment = buildSuggestedPayment(debtorOweSumDetails.getParty(),creditorOweSumDetails.getParty(),suggestedAmount  );
						paymentSet.add(suggestedPayment);
						debtorOweSumDetails.runningBalance = debtorOweSumDetails.runningBalance.subtract(suggestedAmount);
						creditorOweSumDetails.runningBalance = creditorOweSumDetails.runningBalance.subtract(suggestedAmount);
						break;
						// reduce creditor running balance
						// reduce debtor running balance						
						// move to next debtor
					}
					// If creditor can be paid and the debtor has more to pay  
					else
					{
						BigDecimal suggestedAmount =  creditorOweSumDetails.runningBalance;
						Payment suggestedPayment = buildSuggestedPayment(debtorOweSumDetails.getParty(),creditorOweSumDetails.getParty(),suggestedAmount  );
						paymentSet.add(suggestedPayment);
						debtorOweSumDetails.runningBalance = debtorOweSumDetails.runningBalance.subtract(suggestedAmount);
						creditorOweSumDetails.runningBalance = creditorOweSumDetails.runningBalance.subtract(suggestedAmount);
						// pay to current creditor the creditor running balance 
						// reduce creditors running balance
						// reduce debtor running balance						
						// move to next creditor 
						continue;
					}
					
					
					
				}
				else
				{
					continue;
				}
				
				
				
				

				
				
				
				

				

			}

			
		}
		
		return paymentSet;
		
	}

	
	@Override
    public OweSumEventReport getReport(String eventId )
	{
		OweSumEventReport oweSumEventReport = oweSumEventReportDao.getReport(eventId);
		
		
		// TODO : add logic for Debt-closure Payment suggestion  
		
		Set oweSumDetailsSet  = oweSumEventReport.getOwesumDetailsSet();
		
		Set<Payment> suggestedPayments = buildPaymentSuggestions(oweSumDetailsSet) ;
		
		oweSumEventReport.setSuggestedPaymentsSet(suggestedPayments);		
		
		return oweSumEventReport;
	}

	
	
	
}
