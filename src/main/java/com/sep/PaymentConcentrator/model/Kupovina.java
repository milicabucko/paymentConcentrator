package com.sep.PaymentConcentrator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kupovina")
public class Kupovina implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6491747181924195612L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name="korisnik_id")
    private Long korisnikId;

	@Column(name="proizvod_id")
    private Long proizvodId;
	
	@Column(name="tip_proizvoda")
    private String tipProizvoda;

	@Column(name="cena")
    private Double cena;

	@Column(name="broj_meseci")
    private Integer brojMeseci;
    
    private Long tenantID;

    public Long getTenantID() {
		return tenantID;
	}

	public void setTenantID(Long tenantID) {
		this.tenantID = tenantID;
	}

	public Kupovina() {}

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Long getProizvodId() {
        return proizvodId;
    }

    public void setProizvodId(Long proizvodId) {
        this.proizvodId = proizvodId;
    }

    public String getTipProizvoda() {
        return tipProizvoda;
    }

    public void setTipProizvoda(String tipProizvoda) {
        this.tipProizvoda = tipProizvoda;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Integer getBrojMeseci() {
        return brojMeseci;
    }

    public void setBrojMeseci(Integer brojMeseci) {
        this.brojMeseci = brojMeseci;
    }
}
