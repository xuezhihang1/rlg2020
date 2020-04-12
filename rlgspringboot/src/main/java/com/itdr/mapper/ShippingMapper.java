package com.itdr.mapper;

import com.itdr.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    List<Shipping> selectByUserID(@Param("uid") Integer id);

    int deleteByUserIdAndShippingId(@Param("uid") Integer id, @Param("sid") Integer shippingId);

    Shipping selectByPrimaryKey2(@Param("id") Integer sid, @Param("uid") Integer id);

    int update(@Param("uid") Integer id);
}