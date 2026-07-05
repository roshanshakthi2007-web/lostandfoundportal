package com.roshan.lostandfound;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "lost_item")
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String itemName;
    public String category;
    public String location;
}