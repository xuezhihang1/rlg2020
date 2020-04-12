package com.itdr.controller;

import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.pojo.Shipping;
import com.itdr.pojo.User;
import com.itdr.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/portal/shipping/")
public class ShippingController {

    @Autowired
    ShippingService shippingService;

    /**
     * 获取登录用户收货地址
     * @param session
     * @return
     */
    @RequestMapping("create.do")
    public ServerResponse create(HttpSession session){
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if (user == null){
            return ServerResponse.defeatedRS(ConstCode.UserEnum.NO_LOGIN.getDesc());
        }

        return shippingService.create(user);
    }

    /**
     * 删除已登录用户某个地址
     * @param session
     * @param shippingId
     * @return
     */
    @RequestMapping("delete_shipping.do")
    public ServerResponse deleteShipping(HttpSession session, Integer shippingId){
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if (user == null){
            return ServerResponse.defeatedRS(ConstCode.UserEnum.NO_LOGIN.getDesc());
        }

        return shippingService.deleteShipping(user,shippingId);
    }

    /**
     * 添加新地址
     * @param session
     * @param receiver_name
     * @param receiver_phone
     * @param receiver_mobile
     * @param receiver_province
     * @param receiver_city
     * @param receiver_district
     * @param receiver_address
     * @param receiver_zip
     * @return
     */
    @RequestMapping("add_shipping.do")
    public ServerResponse addShipping(HttpSession session, Shipping shipping){
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if (user == null){
            return ServerResponse.defeatedRS(ConstCode.UserEnum.NO_LOGIN.getDesc());
        }
        return shippingService.addShipping(user,shipping);
    }


    @RequestMapping("update_shipping.do")
    public ServerResponse updateShipping(HttpSession session, Shipping shipping){
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if (user == null){
            return ServerResponse.defeatedRS(ConstCode.UserEnum.NO_LOGIN.getDesc());
        }

        return shippingService.updateShipping(user,shipping);
    }

    /**
     * 查找单个收货地址
     * @param sid
     * @param session
     * @return
     */
    @RequestMapping("select_one_address.do")
    public ServerResponse selectOneAddress(Integer sid,HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(ConstCode.UserEnum.NO_LOGIN.getCode(),
                    ConstCode.UserEnum.NO_LOGIN.getDesc());
        }
        return shippingService.selectOneAddress(user,sid);
    }
}
