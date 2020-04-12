package com.itdr.service.impl;

import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.mapper.ShippingMapper;
import com.itdr.pojo.Shipping;
import com.itdr.pojo.User;
import com.itdr.pojo.vo.ShippingVO;
import com.itdr.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService{

    @Autowired
    ShippingMapper shippingMapper;


    @Override
    public ServerResponse create(User user) {
        List<ShippingVO> shippingVOList = new ArrayList<ShippingVO>();
        List<ShippingVO> shippingVO=shippingMapper.selectByUserId(user.getId());

        if (shippingVOList == null){return ServerResponse.defeatedRS("没有数据");}

        return ServerResponse.successRS(shippingVO);
    }

    @Override
    public ServerResponse getShipping(User user) {
        List<ShippingVO> shippingVOList = shippingMapper.selectByUserId(user.getId());
        if (shippingVOList.size() == 0){
            return ServerResponse.defeatedRS(
                    ConstCode.ShippingEnum.EMPTY_SHIPPING.getCode(),
                    ConstCode.ShippingEnum.EMPTY_SHIPPING.getDesc());
        }
        return ServerResponse.successRS(shippingVOList);
    }
}
