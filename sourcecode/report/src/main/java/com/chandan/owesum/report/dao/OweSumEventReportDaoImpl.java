package com.chandan.owesum.report.dao;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chandan.owesum.event.domain.Event;
import com.chandan.owesum.report.domain.OweSumDetails;
import com.chandan.owesum.report.domain.OweSumEventReport;


@Repository
public class OweSumEventReportDaoImpl implements OweSumEventReportDao
{

	
	private final String SELECT_EVENTREPORT_SQL = ""
			+ "with payments as "
			+ "( "
			+ "select pm.payment_amount,  pm.paidby_party_id creditor , pb.beneficiary_party_id  debtor "
			+ ", pb.benefitted_amount pb_benefitted_amount from payment pm join paymentbeneficiary pb on pm.payment_id = pb.payment_id "
			+ "where pm.event_id = ? "
			+ ") "
			+ ",parties as "
			+ "( "
			+ "	select distinct creditor party from payments union select distinct debtor party from payments "
			+ ") "
			+ ",partiesspending as "
			+ "( "
			+ "select  pr.party ,   sum(pm.pb_benefitted_amount) totalspent from "
			+ "	parties pr "
			+ "	left join payments pm on pr.party = pm.creditor "
			+ "	group by pr.party "
			+ ") "
			+ ", "
			+ "debtors as "
			+ "( "
			+ "select  debtor , sum(pb_benefitted_amount) total_debt from payments where creditor <> debtor group by debtor "
			+ ") "
			+ ", creditors as "
			+ "( select creditor ,   sum(pb_benefitted_amount) total_credit from payments  where creditor <> debtor group by creditor) "
			+ " "
			+ "select ps.party ,  creditor, debtor , coalesce(ps.totalspent, 0) totalspent , coalesce(total_debt,0) owes , coalesce(total_credit,0) isOwed , coalesce(total_credit,0) - coalesce(total_debt,0)   balance "
			+ "from partiesspending ps "
			+ "left outer join debtors dr on ps.party = dr.debtor "
			+ "left outer join creditors cr on cr.creditor = dr.debtor "
			+ "order by totalspent desc, party asc";


	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	@Transactional
    public OweSumEventReport  getReport(String eventId)		
	{
		List<OweSumDetails> oweSumDetailsList =   jdbcTemplate.query(SELECT_EVENTREPORT_SQL, new Object[] { eventId },	new OweSumDetailsMapper());
		OweSumEventReport oweSumEventReport = null;
		

		if(oweSumDetailsList  != null && !oweSumDetailsList.isEmpty())
		{
			Event event = new Event();
			event.setEventId(eventId);
			oweSumEventReport = new OweSumEventReport();
			oweSumEventReport.setEvent(event);

			//Comparator<OweSumDetails> oweSumDetailsComparator = (OweSumDetails o1, OweSumDetails o2) -> (o1.getTotalSpent().compareTo(o2.getTotalSpent()));
/*			TreeSet oweSumDetailsSet = new TreeSet(oweSumDetailsComparator);
			oweSumDetailsSet.addAll(oweSumDetailsList);*/
			oweSumEventReport.setOwesumDetailsSet(new HashSet(oweSumDetailsList));
			
			return oweSumEventReport;
		}
		
		return null;
	}
	
	
	
	
	

}

