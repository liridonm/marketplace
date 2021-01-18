package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.Currency;
import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.domain.entities.Uom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCurrency(Currency currency);

    List<Product> findByUom(Uom uom);
}
