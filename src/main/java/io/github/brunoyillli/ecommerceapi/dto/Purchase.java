package io.github.brunoyillli.ecommerceapi.dto;

import java.util.Set;

import io.github.brunoyillli.ecommerceapi.entity.Address;
import io.github.brunoyillli.ecommerceapi.entity.Customer;
import io.github.brunoyillli.ecommerceapi.entity.Order;
import io.github.brunoyillli.ecommerceapi.entity.OrderItem;
import lombok.Data;

@Data
public class Purchase {

	private Customer customer;

	private Address shippingAddress;

	private Address billingAddress;

	private Order order;

	private Set<OrderItem> orderItems;
}
