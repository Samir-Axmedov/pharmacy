package com.maziakowskiadam.pharmacy.controller;

import com.maziakowskiadam.pharmacy.model.Category;
import com.maziakowskiadam.pharmacy.model.Medication;
import com.maziakowskiadam.pharmacy.repository.CategoryRepository;
import com.maziakowskiadam.pharmacy.repository.MedicationRepository;
import com.maziakowskiadam.pharmacy.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MedicationController {

    private MedicationRepository medicationRepository;
    private MedicationService medicationService;
    private CategoryRepository categoryRepository;

    @Autowired
    public MedicationController(MedicationRepository medicationRepository, MedicationService medicationService, CategoryRepository categoryRepository) {
        this.medicationRepository = medicationRepository;
        this.medicationService = medicationService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("medication", new Medication());
        return "add-page";
    }

    @PostMapping("/added")
    public String added(Medication medication) {
        medicationRepository.save(medication);
        return "added-page";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Medication> medicationList = medicationRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("medications", medicationList);
        model.addAttribute("word", new String());
        return "medication-list";
    }

    @GetMapping("/medication/{name}")
    public String medicationByName(Model model, @PathVariable String name) {
        Optional<Medication> medicationByName = medicationRepository.findByNameIgnoreCase(name);
        medicationByName.ifPresent(model::addAttribute);
        return medicationByName.map(medication -> "medication-page").orElse("no-medication-page");
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public String deleteMedication(@PathVariable Long id) {
        medicationRepository.deleteById(id);
        return "redirect:/list";
    }

    @GetMapping("/update")
    public String update() {
        return "redirect:/list";
    }

    @GetMapping("/medication/list")
    public String redirectToList() {
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("medication", new Medication());
        model.addAttribute("id", new Long(id));

        return "edit-page";
    }

    @PostMapping("/edited/{id}")
    public String edited(Medication medication, @PathVariable Long id) {
        medicationService.update(id, medication);
        return "redirect:/list";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("query") String query) {

        List<Medication> searchedList = medicationService.search(query);

        model.addAttribute("searchedList", searchedList);

        return "searched-list";
    }



}