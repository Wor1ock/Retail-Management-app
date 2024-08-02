package com.company.intership.entity;

import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.*;

@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@DiscriminatorValue("INDIVIDUAL_CUSTOMER")
@Table(name = "intership_IndividualCustomer")
@Entity(name = "intership_IndividualCustomer")
@NamePattern("%s|lastName")
public class IndividualCustomer extends Customer {
    private static final long serialVersionUID = -1413106351002197122L;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}