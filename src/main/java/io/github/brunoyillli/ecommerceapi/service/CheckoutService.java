package io.github.brunoyillli.ecommerceapi.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import io.github.brunoyillli.ecommerceapi.dto.PaymentInfo;
import io.github.brunoyillli.ecommerceapi.dto.Purchase;
import io.github.brunoyillli.ecommerceapi.dto.PurchaseResponse;

public interface CheckoutService {

	PurchaseResponse placeOrder(Purchase purchase);
	
	PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
