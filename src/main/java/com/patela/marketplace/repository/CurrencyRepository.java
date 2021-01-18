package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    Optional<Currency> findByNameAndSymbol(String name, String symbol);
}
