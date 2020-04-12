package com.itdr.service.impl;

import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.mapper.ShippingMapper;
import com.itdr.pojo.Shipping;
import com.itdr.pojo.User;
import com.itdr.pojo.vo.ShippingVO2;
import com.itdr.service.ShippingService;
import com.itdr.utils.ObjectToVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    ShippingMapper shippingMapper;

    private Shipping getShipping(User user, String name, String phone, String mobile, String province,
                                 String city, String district, String address, String zip){
        Shipping shipping = new Shipping();
        shipping.setUserId(user.getId());
        shipping.setReceiverName(name);
        shipping.setReceiverPhone(phone);
        shipping.setReceiverMobile(mobile);
        shipping.setReceiverProvince(province);
        shipping.setReceiverCity(city);
        shipping.setReceiverDistrict(district);
        shipping.setReceiverAddress(address);
        shipping.setReceiverZip(zip);
        return shipping;
    }

    @Override
    public ServerResponse create(User user) {
        List<Shipping> list = shippingMapper.selectByUserID(user.getId());

        if (list.size() == 0){
            return ServerResponse.defeatedRS
                    (ConstCode.ShippingEnum.EMPTY_ADDRESS.getCode(),ConstCode.ShippingEnum.EMPTY_ADDRESS.getDesc());
        }

        List<ShippingVO2> li = new ArrayList<>();
        for (Shipping shipping : list) {
            ShippingVO2 shippingVO2 = ObjectToVOUtil.shippingToShippingVO2(shipping);
            li.add(shippingVO2);
        }
        return ServerResponse.successRS(li);
    }

    @Override
    public ServerResponse deleteShipping(User user, Integer shippingId) {
        // 参数合法判断
        if (shippingId == null || shippingId <= 0){
            return ServerResponse.defeatedRS
                    (ConstCode.ShippingEnum.UNLAWFUINESS_PARAM.getCode(),ConstCode.ShippingEnum.UNLAWFUINESS_PARAM.getDesc());
        }

        int i = shippingMapper.deleteByUserIdAndShippingId(user.getId(),shippingId);
        if (i <= 0){
            return ServerResponse.defeatedRS
                    (ConstCode.ShippingEnum.FAILED_DELETE.getCode(),ConstCode.ShippingEnum.FAILED_DELETE.getDesc());
        }

        return ServerResponse.defeatedRS
                (ConstCode.ShippingEnum.SUCCESS_DELETE.getCode(),ConstCode.ShippingEnum.SUCCESS_DELETE.getDesc());
    }

    @Override
    public ServerResponse addShipping(User user, Shipping shipping) {
        if (shipping.getReceiverPhone() != null && shipping.getReceiverPhone().equals("true")){
            int a = shippingMapper.update(user.getId());
        }
        shipping.setUserId(user.getId());
        int i = shippingMapper.insert(shipping);
        if (i <= 0){
            return ServerResponse.defeatedRS
                    (ConstCode.ShippingEnum.FAILED_ADD.getCode(),ConstCode.ShippingEnum.FAILED_ADD.getDesc());
        }
        return ServerResponse.defeatedRS
                (ConstCode.ShippingEnum.SUCCESS_ADD.getCode(),ConstCode.ShippingEnum.SUCCESS_ADD.getDesc());
    }

    @Override
    public ServerResponse updateShipping(User user, Shipping shipping) {
        // 除了id不需要判断非空，如果都是空，默认不更改
        if (shipping.getId() == null){
            return ServerResponse.defeatedRS
                    (ConstCode.ShippingEnum.UNLAWFUINESS_PARAM.getCode(),ConstCode.ShippingEnum.UNLAWFUINESS_PARAM.getDesc());
        }
        // 如果更新的为默认，更改所有其他的为false
        if (shipping.getReceiverPhone() != null && shipping.getReceiverPhone().equals("true")){
            int a = shippingMapper.update(user.getId());
        }
        // 更改地址信息
        int i = shippingMapper.updateByPrimaryKeySelective(shipping);
        if (i <= 0){
            return ServerResponse.defeatedRS
                    (ConstCode.ShippingEnum.FAILED_UPDATE.getCode(),ConstCode.ShippingEnum.FAILED_UPDATE.getDesc());
        }

        return ServerResponse.successRS
                (ConstCode.ShippingEnum.SUCCESS_UPDATE.getCode(),ConstCode.ShippingEnum.SUCCESS_UPDATE.getDesc());
    }


    @Override
    public ServerResponse selectOneAddress(User user, Integer sid) {
        if(sid == null || sid <=0){
            return ServerResponse.defeatedRS
                    (ConstCode.ShippingEnum.UNLAWFUINESS_PARAM.getCode(),ConstCode.ShippingEnum.UNLAWFUINESS_PARAM.getDesc());
        }
        Shipping s = shippingMapper.selectByPrimaryKey2(sid,user.getId());
        if(s == null){
            return ServerResponse.defeatedRS(ConstCode.DEFAULT_FAIL,ConstCode.ShippingEnum.UNLAWFUINESS_PARAM.getDesc());
        }
        return ServerResponse.successRS(s);
    }
}
