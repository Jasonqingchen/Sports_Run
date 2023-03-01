package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.SportUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liuqingchen
 * @date 2022/12/24 20:46
 */
@Mapper
public interface SportuserMapper extends BaseMapper<SportUser> {
    List selectByPhone (String phone);//对手机号进行查重
    List<SportUser> selectByMap(String phone,String sex,String name,String groupzb);// 排序

    Integer updateByPhone (String phone);
}