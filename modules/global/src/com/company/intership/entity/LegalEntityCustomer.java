package com.company.intership.entity;

import javax.persistence.*;

@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@DiscriminatorValue("LEGAL_ENTITY_CUSTOMER")
@Table(name = "intership_LegalEntityCustomer")
@Entity(name = "intership_LegalEntityCustomer")
public class LegalEntityCustomer extends Customer {
    private static final long serialVersionUID = 3846505141079525839L;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}