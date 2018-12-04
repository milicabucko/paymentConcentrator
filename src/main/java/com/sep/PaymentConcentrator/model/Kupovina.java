package com.sep.PaymentConcentrator.model;

import java.io.Serializable;

public class Kupovina implements Serializable {

    private Long korisnikId;

    private Long proizvodId;

    private String tipProizvoda;

    private Double cena;

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
}
