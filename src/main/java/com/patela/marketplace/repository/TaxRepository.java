package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer> {
    Optional<Tax> findByName(String name);
}
