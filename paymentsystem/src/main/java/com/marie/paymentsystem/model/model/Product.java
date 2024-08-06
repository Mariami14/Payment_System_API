package com.marie.paymentsystem.model.model;

import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double minDeposit;
    private Double maxDeposit;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMinDeposit() {
        return minDeposit;
    }

    public void setMinDeposit(Double minDeposit) {
        this.minDeposit = minDeposit;
    }

    public Double getMaxDeposit() {
        return maxDeposit;
    }

    public void setMaxDeposit(Double maxDeposit) {
        this.maxDeposit = maxDeposit;
    }
}
