package com.fabiorapanelo.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.fabiorapanelo.catalog.Product;

@Entity
@Table(name = "TB_ORDER_ITEM")
@XmlRootElement(name = "orderItem")
public class OrderItem {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	private Order order;
	
	private int quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
