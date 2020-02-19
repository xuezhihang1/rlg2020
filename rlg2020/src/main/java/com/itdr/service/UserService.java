package com.itdr.service;

import com.itdr.common.ServerResponse;
import com.itdr.pojo.User;

public interface UserService {

    ServerResponse login(String username, String password);

    ServerResponse<User> register(User u);

    ServerResponse<User> check(String str,String type);

    ServerResponse<User> updateInfo(Integer phonenumber, String email, String raddress,User u);

    ServerResponse<User> forgetGetQuestion(String username);

    ServerResponse<User> forgetCheckAnswer(String username, String question, String answer);

    ServerResponse<User> forgetResetPassword(String username, String passwordNew, String forgetToken);

    ServerResponse<User> restPassword(User user,String passwordOld, String passwordNew);
}
