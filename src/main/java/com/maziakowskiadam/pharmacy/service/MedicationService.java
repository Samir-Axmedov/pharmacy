package com.maziakowskiadam.pharmacy.service;

import com.maziakowskiadam.pharmacy.model.Medication;
import com.maziakowskiadam.pharmacy.repository.MedicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MedicationService {

    private MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Transactional
    public void update() {
        Optional<Medication> byId = medicationRepository.findById(2L);
        Medication ibuprom = byId.get();
        ibuprom.setName("Ibuprom Zatoki");
    }

    @Transactional
    public void delete() {

    }



}
