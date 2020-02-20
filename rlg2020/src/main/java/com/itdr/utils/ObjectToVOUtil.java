package com.itdr.utils;

import com.itdr.pojo.Product;
import com.itdr.pojo.User;
import com.itdr.pojo.vo.ProductVo;
import com.itdr.pojo.vo.UserVo;

import java.util.Properties;

public class ObjectToVOUtil {

    /**
     * 用户类封装
     * @param u
     * @return
     */
    public static UserVo UserToUserVO(User u){
        UserVo uv = new UserVo();
        uv.setId(u.getId());
        uv.setUsername(u.getUsername());
        uv.setPhonenumber(u.getPhonenumber());
        uv.setEmail(u.getEmail());
        uv.setRaddress(u.getRaddress());
        uv.setCreateTime(u.getCreateTime());
        uv.setUpdateTime(u.getUpdateTime());
        return uv;
    }

    /**
     * 商品类封装
     * @param p
     * @return
     */
    public static ProductVo ProductToProductVo(Product p){
        ProductVo pv = new ProductVo();
        pv.setId(p.getId());
        pv.setCid(p.getCid());
        pv.setPname(p.getPname());
        pv.setSubtitle(p.getSubtitle());
        pv.setMainImage(p.getMainImage());
        pv.setDetail(p.getDetail());
        pv.setStatus(p.getStatus());
        pv.setPrice(p.getPrice());
        pv.setPtype(p.getPtype());
        pv.setPnum(p.getPnum());
        pv.setCreateTime(p.getCreateTime());
        pv.setUpdateTime(p.getUpdateTime());
        pv.setImageHost(PropertiesUtil.getValue("ImageHost"));
        return pv;
    }
}
