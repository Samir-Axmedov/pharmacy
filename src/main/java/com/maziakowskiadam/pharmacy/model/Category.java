package com.maziakowskiadam.pharmacy.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull
    private String code;
    @NotNull
    private String country;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Medication> meds;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Medication> getMeds() {
        return meds;
    }

    public void setMeds(List<Medication> meds) {
        this.meds = meds;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
