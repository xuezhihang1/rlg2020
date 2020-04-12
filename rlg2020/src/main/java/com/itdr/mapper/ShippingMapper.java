package com.itdr.mapper;

import com.itdr.pojo.Shipping;
import com.itdr.pojo.vo.ShippingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    List<ShippingVO> selectByUserId( Integer id);
}