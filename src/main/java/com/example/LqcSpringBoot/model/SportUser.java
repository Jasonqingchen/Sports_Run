package com.example.LqcSpringBoot.model;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name="sport_user")
public class SportUser implements Serializable {
    private String name;

    private String sex;

    private String phone;

    private String bz;

    private String id;

    private String groupzb;

    private String number;

    private String min;

    private String status;

    private String sumtime;

    private static final long serialVersionUID = 1L;
}