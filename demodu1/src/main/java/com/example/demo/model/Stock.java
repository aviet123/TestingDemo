package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stock {
    private Long stockID;
    private String name;
    private int quantity;

}
