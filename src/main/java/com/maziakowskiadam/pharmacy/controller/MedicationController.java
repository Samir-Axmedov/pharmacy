package com.maziakowskiadam.pharmacy.controller;

import com.maziakowskiadam.pharmacy.model.Medication;
import com.maziakowskiadam.pharmacy.repository.MedicationRepository;
import com.maziakowskiadam.pharmacy.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class MedicationController {

    private MedicationRepository medicationRepository;
    private MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationRepository medicationRepository, MedicationService medicationService) {
        this.medicationRepository = medicationRepository;
        this.medicationService = medicationService;
    }

    @GetMapping("/add")
    public String add(Model model) {

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
        model.addAttribute("medications", medicationList);

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
    @ResponseBody
    public String update() {

        medicationService.update();
        return "Updated";

    }

    @GetMapping("/medication/list")
    public String redirectToList() {

        return "redirect:/list";
    }


}
