package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends JpaRepository<Support, Integer> {
}
