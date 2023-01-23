package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.SportCp;
import com.example.LqcSpringBoot.model.SportGps;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author liuqingchen
 * @date 2022/12/27 20:30
 */
@Mapper
public interface SportCpMapper extends BaseMapper<SportCp> {
}