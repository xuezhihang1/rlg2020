package com.itdr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.mapper.*;
import com.itdr.pojo.*;
import com.itdr.pojo.vo.CartVO;
import com.itdr.pojo.vo.OrderItemVO;
import com.itdr.pojo.vo.OrderVO;
import com.itdr.pojo.vo.ShippingVO;
import com.itdr.service.CartService;
import com.itdr.service.OrderService;
import com.itdr.utils.BigDecimalUtil;
import com.itdr.utils.ObjectToVOUtil;
import com.itdr.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrederServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    CartService cartService;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    ShippingMapper shippingMapper;

    //订单编号生成规则
    private Long getNo(){
        long round = Math.round(Math.random() * 100);
        long l = System.currentTimeMillis()+round;
        return l;
    }

    //返回成功数据
    private  OrderVO getOrderVO(Order o, Integer shippingId, List<OrderItemVO> list, ShippingVO shippingVO){
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderNo(o.getOrderNo());
        orderVO.setShippingId(shippingId);
        orderVO.setPayment(o.getPayment());
        orderVO.setPaymentType(o.getPaymentType());
        orderVO.setPostage(o.getPostage());
        orderVO.setStatus(o.getStatus());
        orderVO.setOrderItemVOList(list);
        orderVO.setShippingVO(shippingVO);
        orderVO.setImageHost(PropertiesUtil.getValue("ImageHost"));

        return orderVO;
    }

    // 根据订单详情集合获取OrderItemVO集合
    private List<OrderItemVO> getOrderItemVO(List<OrderItem> itemList){
        List<OrderItemVO> itemVOList = new ArrayList<>();
        for (OrderItem orderItem : itemList) {
            OrderItemVO orderItemVO = ObjectToVOUtil.orderItemToOrderItemVO(orderItem);
            itemVOList.add(orderItemVO);
        }
        return itemVOList;
    }

    @Override
    public ServerResponse create(User user, Integer shippingId) {
        //参数判断
        if(shippingId == null || shippingId<0){
            return ServerResponse.defeatedRS(ConstCode.DEFAULT_FAIL,ConstCode.UNLAWFULNESS_PARAM);
        }

        //判断当前用户购物车中有没有被选中的数据
        ServerResponse over = cartService.over(user);
        if (!over.isSuccess()){
            return ServerResponse.defeatedRS(
                    ConstCode.CartEnum.NOSELECT_PRODUCT.getCode(),
                    ConstCode.CartEnum.NOSELECT_PRODUCT.getDesc());
        }

        //根据用户名获取购物车信息
        List<Cart> cartList = cartMapper.selectByUserID(user.getId());
        CartVO cartVO = ((CartServiceImpl) cartService).getCartVO(cartList);

        //创建一个订单
        Long no = getNo();
        Order o = new Order();
        o.setUserId(user.getId());
        o.setOrderNo(no);
        o.setShippingId(shippingId);
        o.setPayment(cartVO.getCartTotalPrice());
        o.setPaymentType(1);
        o.setPostage(0);
        o.setStatus(10);
        //把创建的订单对象存储到数据库中
        int i = orderMapper.insert(o);
        if(i<=0){
            return ServerResponse.defeatedRS("订单创建失败");
        }

        //创建订单详情
        List<OrderItemVO> itemVOList = new ArrayList<OrderItemVO>();
        for (Cart cart : cartList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setUserId(user.getId());
            orderItem.setOrderNo(o.getOrderNo());

            if(cart.getChecked() == 1){
                Product product = productMapper.selectByPrimaryKey(cart.getProductId());
                if(product.getStatus() == 1 && cart.getQuantity() <= product.getStock()){
                    orderItem.setProductId(cart.getProductId());
                    orderItem.setProductName(product.getName());
                    orderItem.setProductImage(product.getMainImage());
                    orderItem.setCurrentUnitPrice(product.getPrice());
                    orderItem.setQuantity(cart.getQuantity());
                    orderItem.setTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(),cart.getQuantity().doubleValue()));
                }
                //把创建的订单详情对象存储到数据库中
                int i2 = orderItemMapper.insert(orderItem);
                if(i2<=0){
                    return ServerResponse.defeatedRS("订单详情创建失败");
                }

                OrderItemVO orderItemVO = ObjectToVOUtil.orderItemToOrderItemVO(orderItem);
                itemVOList.add(orderItemVO);
            }
        }

        //清空购物车数据
        int i3 = cartMapper.deleteByUserIDAndChecked(user.getId());

        //返回成功数据
        Shipping shipping = shippingMapper.selectByPrimaryKey(shippingId);
        if(shipping == null){
            return ServerResponse.defeatedRS("地址不存在!");
        }
        ShippingVO shippingVO = ObjectToVOUtil.shippingToShippingVO(shipping);
        OrderVO orderVO = getOrderVO(o, shippingId, itemVOList, shippingVO);

        return ServerResponse.successRS(orderVO);
    }



    /**
     * 获取订单信息
     * @param user
     * @return
     */
    @Override
    public ServerResponse getOrderCartProduct(User user) {
        List<OrderItemVO> itemVoList =new ArrayList<OrderItemVO>();
        List<OrderItemVO> orderItemVoList=orderItemMapper.selectByUserid(user.getId());

        if (itemVoList == null){return ServerResponse.defeatedRS("没有数据");}

        return ServerResponse.successRS(orderItemVoList);
    }

    @Override
    public ServerResponse list(User user, Integer pageSize, Integer pageNum) {
        List<OrderVO> orderVOList = new ArrayList<>();

        // 获取用户的所有订单
        // 分页处理放在查询语句之上
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orderList = orderMapper.selectByUid(user.getId());

        // 循环创建OrderVO对象
        for (Order o : orderList) {
            List<OrderItem> orderItemList = orderItemMapper.selectByOrderNoAndUserId(o.getOrderNo(), user.getId());
            // 判断是否有订单
            if (orderItemList.size() == 0){
                return ServerResponse.defeatedRS
                        (ConstCode.OrderEnum.NOT_FIND_ORDER.getCode(),ConstCode.OrderEnum.NOT_FIND_ORDER.getDesc());
            }

            List<OrderItemVO> orderItemVOList = this.getOrderItemVO(orderItemList);
            Shipping shipping = shippingMapper.selectByPrimaryKey(o.getShippingId());
            ShippingVO shippingVO = ObjectToVOUtil.shippingToShippingVO(shipping);

            // 封装OrderVO对象，添加至集合中
            OrderVO orderVO = getOrderVO(o, o.getShippingId(),orderItemVOList , shippingVO);

            orderVOList.add(orderVO);
        }

        PageInfo pageInfo = new PageInfo(orderList);
        // 用setList改变集合中内容为orderVOList
        pageInfo.setList(orderVOList);

        return ServerResponse.successRS(pageInfo);
    }
}
