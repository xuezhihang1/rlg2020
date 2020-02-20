package com.itdr.controller;


import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.pojo.User;
import com.itdr.pojo.vo.UserVo;
import com.itdr.service.UserService;
import com.itdr.utils.ObjectToVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/portal/user/")
public class UserController {

    @Autowired
    UserService userService;



    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("login.do")
    public ServerResponse<User> login(String username, String password, HttpSession session){

        ServerResponse sr = userService.login(username,password);
        //登陆成功，在session中保存用户数据
        if (sr.isSuccess()){
            session.setAttribute("user",sr.getData());
        }
         return sr;
    }


    /**
     * 用户注册
     * @param u
     * @return
     */
    @RequestMapping("register.do")
    public ServerResponse<User> register(User u){
        return userService.register(u);
    }


    /**
     * 获取用户所有信息
     * @param session
     * @return
     */
    @RequestMapping("getall.do")
    public ServerResponse<User> getall(HttpSession session){
        //判断用户是否登录
        User us = (User) session.getAttribute("user");
        if (us == null){
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc());
        }
        return ServerResponse.successRS(us);
    }

    /**
     * 获取登录用户信息
     * @param session
     * @return
     */
    @RequestMapping("getinfo.do")
    public ServerResponse<User> getInfo(HttpSession session){
        User us = (User) session.getAttribute("user");
         UserVo uv = ObjectToVOUtil.UserToUserVO(us);
        return ServerResponse.successRS(uv);
    }

    /**
     * 检查用户名是否有效
     * @param str,type
     * @return
     */
    @RequestMapping("check.do")
    public ServerResponse<User> check(String str,String type){
        return userService.check(str,type);
    }

    /**
     * 登录状态更新个人信息
     * @param phonenumber
     * @param email
     * @param raddress
     * @return
     */
    @RequestMapping("updateinfo.do")
    public ServerResponse<User> updateinfo(Integer phonenumber,String email,String raddress,HttpSession session){

        //判断用户是否登录
        User us = (User) session.getAttribute("user");
        if (us == null){
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc());
        }
        return userService.updateInfo(phonenumber,email,raddress,us);
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("logout.do")
    public ServerResponse<User> logout(HttpSession session){
        session.removeAttribute("user");
        return ServerResponse.successRS(ConstCode.UserEnum.LOGOUT.getDesc());
    }

    /**
     * 忘记密码
     * @param username
     * @return
     */
    @RequestMapping("forget_get_question.do")
    public ServerResponse<User> forgetGetQuestion(String username){
        return userService.forgetGetQuestion(username);
    }

    /**
     * 提交问题答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping("forget_check_answer.do")
    public ServerResponse<User> forgetCheckAnswer(String username,String question,String answer){
        return userService.forgetCheckAnswer(username,question,answer);
    }

    /**
     * 重置密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @param session
     * @return
     */
    @RequestMapping("forget_reset_password.do")
    public ServerResponse<User> forgetResetPassword(String username,String passwordNew,String forgetToken,HttpSession session){
        ServerResponse<User> userServerResponse = userService.forgetResetPassword(username,passwordNew,forgetToken);
        if (userServerResponse.isSuccess()){
            session.removeAttribute("user");
        }
        return userServerResponse;
    }

    /**
     * 登录状态重置密码
     * @param passwordOld
     * @param passwordNew
     * @param session
     * @return
     */
    @RequestMapping("reset_password.do")
    public ServerResponse<User> resetPassword(String passwordOld,String passwordNew,HttpSession session){
        //判断用户是否登录
        User user = (User) session.getAttribute("user");
        if (user == null){
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.UserEnum.NO_LOGIN.getDesc());
        }
        return userService.restPassword(user,passwordOld,passwordNew);
    }
}
