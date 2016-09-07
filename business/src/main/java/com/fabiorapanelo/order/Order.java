package com.fabiorapanelo.order;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TB_ORDER")
@XmlRootElement(name = "order")
public class Order {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "ORDER_ID")
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<OrderItem> items = new HashSet<OrderItem>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

}
