package com.itdr.service;

import com.itdr.common.ServerResponse;
import com.itdr.pojo.OrderItem;
import com.itdr.pojo.User;
import com.itdr.pojo.vo.OrderItemVO;

import java.util.List;

public interface OrderService {
    ServerResponse create(User user, Integer shippingId);

    ServerResponse getOrderCartProduct(User user);

    ServerResponse list(User user, Integer pageSize, Integer pageNum);

}
