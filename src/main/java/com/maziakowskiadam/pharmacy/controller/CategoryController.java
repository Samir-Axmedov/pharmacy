package com.maziakowskiadam.pharmacy.controller;

import com.maziakowskiadam.pharmacy.model.Category;
import com.maziakowskiadam.pharmacy.model.Medication;
import com.maziakowskiadam.pharmacy.repository.CategoryRepository;
import com.maziakowskiadam.pharmacy.repository.MedicationRepository;
import com.maziakowskiadam.pharmacy.service.CategoryService;
import com.maziakowskiadam.pharmacy.service.MedicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    private MedicationRepository medicationRepository;
    private MedicationService medicationService;
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;


    public CategoryController(MedicationRepository medicationRepository, MedicationService medicationService,
                              CategoryRepository categoryRepository, CategoryService categoryService) {

        this.medicationRepository = medicationRepository;
        this.medicationService = medicationService;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @GetMapping("/add-category")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "add-category-page";
    }

    @PostMapping("/category-added")
    public String categoryAdded(Category category) {
        categoryRepository.save(category);
        return "redirect:/category-list";
    }

    @GetMapping("/category-list")
    public String list(Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("word", new String());
        return "category-list";
    }

    @GetMapping("/edit-category/{id}")
    public String editCategory(Model model, @PathVariable Long id) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());
        model.addAttribute("id", new Long(id));
        return "edit-category-page";
    }

    @PostMapping("/edited-category/{id}")
    public String edited(Category category, @PathVariable Long id) {
        categoryService.update(id, category);
        return "redirect:/category-list";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteMedication(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/category-list";
    }

    @PostMapping("/search-by-category")
    public String search(Model model, @RequestParam("query1") String query) {

        Optional<Category> category = categoryRepository.findAllByCountry(query);
        Category cat = category.get();
        List<Medication> categories = medicationRepository.findAllByCategory(cat);
        model.addAttribute("cat", cat);
        model.addAttribute("categories", categories);
        return "searched-category-list";
    }

}
