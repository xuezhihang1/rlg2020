package com.itdr.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderAndProductVO {
    private List<OrderItemVO> orderItemVoList;

    private String imageHost;

    private BigDecimal productTotalPrice;
}
