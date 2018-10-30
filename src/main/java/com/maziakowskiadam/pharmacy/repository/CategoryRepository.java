package com.maziakowskiadam.pharmacy.repository;

import com.maziakowskiadam.pharmacy.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCountryContaining(String query);

    Optional<Category> findByCountry(String query);

    Optional<Category> findByCountryContains(String query);

    Optional<Category> findAllByCountry(String query);
}
