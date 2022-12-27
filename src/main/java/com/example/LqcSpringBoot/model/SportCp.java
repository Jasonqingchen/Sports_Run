package com.example.LqcSpringBoot.model;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@Table(name = "Sport_cp")
public class SportCp implements Serializable {
    private Integer id;

    private String cpname;

    private Date cpendtime;

    private String userid;

    private String bz;

    private Date starttime;

    private Date endtime;

    private static final long serialVersionUID = 1L;

}