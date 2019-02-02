package com.sep.PaymentConcentrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sep.PaymentConcentrator.model.NaucnaCentrala;

@Repository
public interface NaucnaCentralaRepository extends JpaRepository<NaucnaCentrala, Long>{

}
