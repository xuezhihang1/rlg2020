package com.itdr.service;

import com.itdr.common.ServerResponse;
import com.itdr.pojo.Shipping;
import com.itdr.pojo.User;

public interface ShippingService {
    ServerResponse create(User user);

    ServerResponse deleteShipping(User user, Integer shippingId);

    ServerResponse addShipping(User user, Shipping shipping);

    ServerResponse updateShipping(User user, Shipping shipping);

    ServerResponse selectOneAddress(User user, Integer sid);
}
