package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.SportTb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author liuqingchen
 * @date 2023/05/26 09:55
 */
@Mapper
public interface SportTbMapper extends BaseMapper<SportTb> {
    List<SportTb> selectBySfzhm(String sfzhm);// search user by sfzhm
    List<SportTb> selecNameLike(String name);//search user by name and like select
}