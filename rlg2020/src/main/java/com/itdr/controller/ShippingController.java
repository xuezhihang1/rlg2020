package com.itdr.controller;


import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
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

    @RequestMapping("create.do")
    public ServerResponse create(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc());
        }

        return shippingService.create(user);
    }

    @RequestMapping("getShipping.do")
    public ServerResponse getShipping(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc());
        }

        return shippingService.getShipping(user);
    }
}
