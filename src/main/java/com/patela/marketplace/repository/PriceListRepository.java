package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Integer> {
}
