package com.sep.PaymentConcentrator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "naucna_centrala")
public class NaucnaCentrala implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8784593276181128553L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id; //merchantId

	@Column(name = "name")
	private String name;
	
	@Column(name = "merchant_password")
	private String merchantPassword;

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public NaucnaCentrala() {
	}

	public NaucnaCentrala(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
