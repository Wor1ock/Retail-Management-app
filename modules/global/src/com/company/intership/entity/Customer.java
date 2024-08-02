package com.company.intership.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EmbeddedParameters;

import javax.persistence.*;

@Table(name = "INTERSHIP_CUSTOMER")
@Entity(name = "intership_Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = -1467985972714221375L;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Embedded
    @EmbeddedParameters(nullAllowed = false)
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "ADDRESS_STREET")),
            @AttributeOverride(name = "building", column = @Column(name = "ADDRESS_BUILDING"))
    })
    private Address address;

    @Column(name = "EMAIL", unique = true)
    private String email;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer")
    private ExtendedUser extendedUser;

    public ExtendedUser getExtendedUser() {
        return extendedUser;
    }

    public void setExtendedUser(ExtendedUser extendedUser) {
        this.extendedUser = extendedUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}