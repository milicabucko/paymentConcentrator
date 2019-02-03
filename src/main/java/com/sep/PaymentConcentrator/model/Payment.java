package com.sep.PaymentConcentrator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "tenant_id")
	private Long tenantID;

	@Column(name = "user_id")
	private Long userID;
	
	@Column(name = "magazin_id")
	private Long casopisID;
	
	@Column(name = "izdanje_magazina_id")
    private Long izdanjeMagazinaId;

	@Column
	private Long radId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTenantID() {
		return tenantID;
	}

	public void setTenantID(Long tenantID) {
		this.tenantID = tenantID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getCasopisID() {
		return casopisID;
	}

	public void setCasopisID(Long casopisID) {
		this.casopisID = casopisID;
	}

	public Long getIzdanjeMagazinaId() {
		return izdanjeMagazinaId;
	}

	public void setIzdanjeMagazinaId(Long izdanjeMagazinaId) {
		this.izdanjeMagazinaId = izdanjeMagazinaId;
	}

	public Long getRadId() {
		return radId;
	}

	public void setRadId(Long radId) {
		this.radId = radId;
	}

}
