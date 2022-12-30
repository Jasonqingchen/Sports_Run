package com.example.LqcSpringBoot.model;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
@Data
@Table(name = "sport_cp")
public class SportCp implements Serializable {
    private String id;

    private String cpname;

    private String cpendtime;

    private String userid;

    private String bz;

    private String starttime;

    private String endtime;

    private static final long serialVersionUID = 1L;
}