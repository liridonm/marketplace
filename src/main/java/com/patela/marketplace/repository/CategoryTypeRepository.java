package com.patela.marketplace.repository;

import com.patela.marketplace.domain.entities.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, Integer> {
    Optional<CategoryType> findByName(String name);
}
