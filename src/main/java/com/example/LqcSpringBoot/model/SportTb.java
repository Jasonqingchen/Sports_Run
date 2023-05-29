package com.example.LqcSpringBoot.model;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
@Data
@Table(name = "sport_tb")
public class SportTb implements Serializable {
    private String id;

    private String name;

    private String phone;

    private String jjlxr;

    private String jjlxrdh;

    private String zb;

    private String sex;

    private String wxm;

    private String sfzhm;

    private String gj;

    private String yfcm;

    private String email;

    private String zz;

    private String sportname;

    private static final long serialVersionUID = 1L;

}