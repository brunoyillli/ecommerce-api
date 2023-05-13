package io.github.brunoyillli.ecommerceapi.service;

import io.github.brunoyillli.ecommerceapi.dto.Purchase;
import io.github.brunoyillli.ecommerceapi.dto.PurchaseResponse;

public interface CheckoutService {

	PurchaseResponse placeOrder(Purchase purchase);
}
