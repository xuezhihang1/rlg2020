package com.itdr.controller;

import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.pojo.User;
import com.itdr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal/order/")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 创建订单
     * @param session
     * @param shippingId
     * @return
     */
    @RequestMapping("/create.do")
    public ServerResponse create(HttpSession session,Integer shippingId){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc());
        }

        return orderService.create(user,shippingId);
    }

    /**
     * 获取订单信息
     * @param session
     * @return
     */
    @RequestMapping("get_order_cart_product.do")
    public ServerResponse getOrderCartProduct(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc());
        }
        return  orderService.getOrderCartProduct(user);
    }

    /**
     * 获取订单列表（用户）
     * @param session
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping("list.do")
    public ServerResponse list(HttpSession session,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize,
                               @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ServerResponse.defeatedRS(ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc());
        }
        return  orderService.list(user,pageSize,pageNum);
    }
}
