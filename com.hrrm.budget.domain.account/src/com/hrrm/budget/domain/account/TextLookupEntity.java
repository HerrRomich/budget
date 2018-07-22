package com.hrrm.budget.domain.account;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TextLookupEntity extends AccountBaseEntity {

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
