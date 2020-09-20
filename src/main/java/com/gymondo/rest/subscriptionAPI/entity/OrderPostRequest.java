package com.gymondo.rest.subscriptionAPI.entity;

import java.util.List;

public class OrderPostRequest {
	
	public int userId;
	public List<BuyProduct> products;

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setProducts(List<BuyProduct> products) {
		this.products = products;
	}

	public static class BuyProduct{
		public int productId;
		public int productQuantity;
		
		public int getProductId() {
			return productId;
		}

		public int getProductQuantity() {
			return productQuantity;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}

		public void setProductQuantity(int productQuantity) {
			this.productQuantity = productQuantity;
		}

	}

}

