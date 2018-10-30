package com.maziakowskiadam.pharmacy.service;

import com.maziakowskiadam.pharmacy.model.Medication;
import com.maziakowskiadam.pharmacy.repository.MedicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {

    private MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Transactional
    public void update(Long id, Medication medication) {

        Optional<Medication> byId = medicationRepository.findById(id);
        Medication zmiana = byId.get();
        zmiana.setName(medication.getName());
        zmiana.setProducer(medication.getProducer());
        zmiana.setRefundation(medication.getRefundation());

    }

    public List<Medication> search(String query) {

        List<Medication> searchedByName = medicationRepository.findAllByNameContaining(query);
        List<Medication> searchedByProducer = medicationRepository.findAllByProducerContaining(query);
        List<Medication> searchedByRefundation = medicationRepository.findAllByRefundationContaining(query);
        List<Medication> searchedByAll = new ArrayList<>();
        searchedByAll.addAll(searchedByName);
        searchedByAll.addAll(searchedByProducer);
        searchedByAll.addAll(searchedByRefundation);
        return searchedByAll;

    }
}
