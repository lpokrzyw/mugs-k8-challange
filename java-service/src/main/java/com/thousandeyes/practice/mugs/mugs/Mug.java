package com.thousandeyes.practice.mugs.mugs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MUGS")
public class Mug {
    @Id
    private int id;
    @Column(name = "PRICE", nullable = false)
    private double price;

    public Mug() {
    }

    public Mug(int id, double price) {
        super();
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}
