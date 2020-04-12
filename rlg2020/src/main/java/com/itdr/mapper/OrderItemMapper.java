package com.itdr.mapper;

import com.itdr.pojo.OrderItem;
import com.itdr.pojo.vo.OrderItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItem> selectByOrderNoAndUserId(@Param("orderNo") Long orderNo,
                                             @Param("userId") Integer uid);

    List<OrderItemVO> selectByUserid(Integer id);

}