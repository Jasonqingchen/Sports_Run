package com.example.LqcSpringBoot.model;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
@Data
@Table(name = "sport_gps")
public class SportGps implements Serializable {
    private String gpsid;

    private String bz;

    private String userid;

    private String cpid;

    private String gpstime;

    private String username;

    private String sumtime;

    private String sex;

    private String groupzb;

    private String cpname;

    private String phone;

    private static final long serialVersionUID = 1L;
}