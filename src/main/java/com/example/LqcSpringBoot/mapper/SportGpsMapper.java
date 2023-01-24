package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.SportGps;

import java.util.List;

/**
 * @author liuqingchen
 * @date 2023/1/18 13:52
 */
public interface SportGpsMapper extends BaseMapper<SportGps> {
    List<SportGps> selectGpByPhoneAndCpid(String phone,String cpid); //验证该选手是否已经打卡
    List<SportGps>  selectByPhone(String phone);
}