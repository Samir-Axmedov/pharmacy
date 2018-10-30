package com.maziakowskiadam.pharmacy.service;

import com.maziakowskiadam.pharmacy.model.Category;
import com.maziakowskiadam.pharmacy.repository.CategoryRepository;
import com.maziakowskiadam.pharmacy.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;
    MedicationRepository medicationRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, MedicationRepository medicationRepository) {
        this.categoryRepository = categoryRepository;
        this.medicationRepository = medicationRepository;
    }

    @Transactional
    public void update(Long id, Category category) {
        Optional<Category> byId = categoryRepository.findById(id);
        Category zmiana = byId.get();
        zmiana.setCode(category.getCode());
        zmiana.setCountry(category.getCountry());
    }

    @Transactional
    public void delete(Long id) {

        Optional<Category> category = categoryRepository.findById(id);
        Category k = category.get();
        medicationRepository.deleteAllByCategory(k);
        categoryRepository.deleteById(id);

    }


}
