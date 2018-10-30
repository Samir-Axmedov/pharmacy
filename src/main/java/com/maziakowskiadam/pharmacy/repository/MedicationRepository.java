package com.maziakowskiadam.pharmacy.repository;

import com.maziakowskiadam.pharmacy.model.Category;
import com.maziakowskiadam.pharmacy.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Optional<Medication> findByNameIgnoreCase(String name);

    List<Medication> findAllByNameContaining(String query);

    List<Medication> findAllByProducerContaining(String query);

    List<Medication> findAllByRefundationContaining(String query);

    List<Medication> deleteAllByCategory(Category category);

    List<Medication> findAllByCategory(Category category);

}
