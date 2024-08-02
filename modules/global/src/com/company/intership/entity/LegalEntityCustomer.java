package com.company.intership.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "intership_LegalEntityCustomer")
public class LegalEntityCustomer extends Customer {
    private static final long serialVersionUID = 3846505141079525839L;

    @NotNull
    @Column(name = "COMPANY_NAME", nullable = false)
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}