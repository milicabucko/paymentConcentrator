package com.sep.PaymentConcentrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sep.PaymentConcentrator.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
