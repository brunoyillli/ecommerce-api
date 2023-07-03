package io.github.brunoyillli.ecommerceapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import io.github.brunoyillli.ecommerceapi.dto.PaymentInfo;
import io.github.brunoyillli.ecommerceapi.dto.Purchase;
import io.github.brunoyillli.ecommerceapi.dto.PurchaseResponse;
import io.github.brunoyillli.ecommerceapi.service.CheckoutService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	private CheckoutService checkoutService;
	
	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}
	
	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
		return purchaseResponse;
	}
	
	@PostMapping("/payment-intent")
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException{
		PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
		String paymentStr = paymentIntent.toJson();
		return new ResponseEntity<>(paymentStr, HttpStatus.OK);
	}
}
