package com.example.LqcSpringBoot.model;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "sport_km")
public class SportKm implements Serializable {
    private String id;

    private String name;

    private String valu;

    private String label;

    private static final long serialVersionUID = 1L;
}