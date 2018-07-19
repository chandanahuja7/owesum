package com.chandan.owesum.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chandan.owesum.payment.dao.PaymentDao;
import com.chandan.owesum.payment.domain.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDAO;

	
	@Override
    public boolean addPayment(Payment payment )
    {
		paymentDAO.addPayment(payment);
		return true;
    }


	
	@Override
    public Payment getPayment(String paymentId )
	{
		Payment payment = paymentDAO.getById(paymentId);
		return payment;
	}

	
	
	/*@Override
	public boolean addPriceLog(PriceLog priceLog) {

		// PriceRequest priceRequest = getPriceLog(priceLog);

		PriceRequest priceRequest = priceLog.getPriceRequest();
		priceRequestDAO.addPriceRequest(priceRequest);

		if (priceRequest.getChannelTpCd() == 2) {

			List<OrderLine> orderLines = priceRequest.getOrderLines();

			if (orderLines != null) {
				for (OrderLine orderLine : orderLines) {
					orderLineDAO.addOrderLine(orderLine);
					
					
					ComputedPrice2 computedFrieghtPrice = orderLine   .getcom.getComputedFreightPrice();
					if (computedFrieghtPrice != null) {

						computedPrice2DAO.addComputedPrice2(computedFrieghtPrice);

					}
					
					
					
					
					
				}
			}

			

			
			
			
		} else // Channel.equals("LMNPB-PE-BATCH")) OR Channel.equals("WEB"))
		{

			List<Consignment> consignments = priceRequest.getConsignments();
			if (consignments != null) {
				for (Consignment consignment : consignments) {

					consignmentDAO.addConsignment(consignment);

					// Consignment Items
					List<ConsignmentItem> consignmentItems = consignment.getConsignmentItems();
					if (consignmentItems != null) {
						for (ConsignmentItem consignmentItem : consignmentItems) {

							consignmentItemDAO.addConsignmentItem(consignmentItem);
						}
					}

					// Additional Services
					List<AdditionalService> additionalServices = consignment.getAdditionalServices();
					if (additionalServices != null) {
						for (AdditionalService additionalService : additionalServices) {

							additionalServiceDAO.addAdditionalService(additionalService);
						}
					}

					ComputedPrice2 computedFrieghtPrice = consignment.getComputedFreightPrice();
					if (computedFrieghtPrice != null) {

						computedPrice2DAO.addComputedPrice2(computedFrieghtPrice);

					}

				}
			}

		}
		return true;

	}
*/
}
