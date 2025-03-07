package com.converter.CurrencyConverter.Repository;


import com.converter.CurrencyConverter.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {


}
