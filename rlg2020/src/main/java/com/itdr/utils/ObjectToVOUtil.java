package com.itdr.utils;

import com.itdr.pojo.User;
import com.itdr.pojo.vo.UserVo;

public class ObjectToVOUtil {

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
}
