package com.chandan.owesum.report.engine;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.chandan.owesum.party.domain.Party;
import com.chandan.owesum.payment.domain.Payment;
import com.chandan.owesum.payment.domain.PaymentBeneficiary;
import com.chandan.owesum.report.domain.OweSumDetails;

public class PaymentRecommender {

	
	public static  Set<Payment> buildPaymentRecommendations(Set<OweSumDetails> oweSumDetailsSet) 
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
						// Need to Pay pay to current Creditor entire amount which debtor need to pay
						BigDecimal suggestedAmount =  debtorOweSumDetails.runningBalance;
						
						
						// pay to current Creditor entire amount which debtor need to pay
						// reduce creditor running balance
						// reduce debtor running balance		
						processRecommendedPayment( debtorOweSumDetails,  creditorOweSumDetails,  suggestedAmount ,  paymentSet );
						
						// move to next debtor
						break;
				
					}
					// If creditor can be paid and the debtor has more to pay  
					else
					{
						// Need to pay to current creditor the creditor running balance
						BigDecimal suggestedAmount =  creditorOweSumDetails.runningBalance;
						
						
						// pay to current creditor the creditor running balance 
						// reduce creditors running balance
						// reduce debtor running balance	
						processRecommendedPayment( debtorOweSumDetails,  creditorOweSumDetails,  suggestedAmount ,  paymentSet );

					
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
	
	
	
	private static void processRecommendedPayment(OweSumDetails debtorOweSumDetails, OweSumDetails creditorOweSumDetails, BigDecimal suggestedAmount , Set<Payment> paymentSet )
	{
		Payment suggestedPayment = buildSuggestedPayment(debtorOweSumDetails.getParty(),creditorOweSumDetails.getParty(),suggestedAmount  );
		paymentSet.add(suggestedPayment);
		reduceRunningBalance( debtorOweSumDetails,  creditorOweSumDetails,  suggestedAmount);		
	}

	
	private static void reduceRunningBalance(OweSumDetails debtorOweSumDetails, OweSumDetails creditorOweSumDetails, BigDecimal suggestedAmount)
	{
		debtorOweSumDetails.runningBalance = debtorOweSumDetails.runningBalance.subtract(suggestedAmount);
		creditorOweSumDetails.runningBalance = creditorOweSumDetails.runningBalance.subtract(suggestedAmount);
	}
	
	private static Payment buildSuggestedPayment(Party payer, Party payee, BigDecimal amount)
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

	
	
}
