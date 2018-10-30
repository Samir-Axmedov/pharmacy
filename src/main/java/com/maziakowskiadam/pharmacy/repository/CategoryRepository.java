package com.maziakowskiadam.pharmacy.repository;

import com.maziakowskiadam.pharmacy.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findAllByCountry(String query);
}
