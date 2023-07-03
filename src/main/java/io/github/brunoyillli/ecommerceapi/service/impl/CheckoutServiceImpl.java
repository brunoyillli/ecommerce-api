package io.github.brunoyillli.ecommerceapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import io.github.brunoyillli.ecommerceapi.dto.PaymentInfo;
import io.github.brunoyillli.ecommerceapi.dto.Purchase;
import io.github.brunoyillli.ecommerceapi.dto.PurchaseResponse;
import io.github.brunoyillli.ecommerceapi.entity.Customer;
import io.github.brunoyillli.ecommerceapi.entity.Order;
import io.github.brunoyillli.ecommerceapi.entity.OrderItem;
import io.github.brunoyillli.ecommerceapi.repository.CustomerRepository;
import io.github.brunoyillli.ecommerceapi.service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService{

	private CustomerRepository customerRepository;
	
	public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}") String secretKey) {
		this.customerRepository = customerRepository;
	
		Stripe.apiKey = secretKey;
	}
	
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		Order order = purchase.getOrder();
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		Customer customer = purchase.getCustomer();
		
		String theEmail = customer.getEmail();

		Customer customerFromDB = customerRepository.findByEmail(theEmail);
		
		if(customerFromDB != null) {
			customer = customerFromDB;
		}
		
		customer.add(order);
		
		customerRepository.save(customer);
		
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		return UUID.randomUUID().toString();
	}

	@Override
	public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
		
		List<String> paymentMethodTypes = new ArrayList<>();
		paymentMethodTypes.add("card");
		
		Map<String, Object> params = new HashMap<>();
		params.put("amount", paymentInfo.getAmount());
		params.put("currency", paymentInfo.getCurrency());
		params.put("payment_method_types", paymentMethodTypes);
		
		return PaymentIntent.create(params);

	}

}
