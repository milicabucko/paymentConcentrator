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
@Table(name = "naucna_centrala")
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

}
