package com.haha.xixi.controller;


import com.alibaba.fastjson.JSON;
import com.haha.xixi.json.AjaxJson;
import com.haha.xixi.json.JWT;
import com.haha.xixi.json.UserJson;
import com.haha.xixi.model.OauthClientDetailsModel;
import com.haha.xixi.model.UserBaseExtModel;
import com.haha.xixi.model.UserBaseModel;
import com.haha.xixi.service.IoauthClientDetailsService;
import com.haha.xixi.service.IuserBaseExtService;
import com.haha.xixi.service.IuserBaseService;
import com.haha.xixi.utils.Base64codeUtil;
import com.haha.xixi.utils.BcryptUtil;
import com.haha.xixi.utils.EncrypMD5Util;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Pan Weilong
 * @date 2019/6/20 15:06
 * @description: 接口.
 */
@RestController
public class HelloController {

    @Autowired
    IuserBaseService userService;

    @Autowired
    IuserBaseExtService userExtService;

    @Autowired
    IoauthClientDetailsService oauthService;

    /**
     * @author weilai
     * @date 2019年4月3日下午2:45:31
     * @desc <li>向cbm_mag_user_info 数据表增加记录
     */
    @PostMapping(value = "/login/white/addUser")
    public AjaxJson addUser(@RequestBody UserJson userJson) {
        AjaxJson json = new AjaxJson();
        UserBaseModel user = (UserBaseModel) this.userService.loadUserByUsername(userJson.getUsername());
        Assert.isNull(user, userJson.getUsername() + " :username already exists");
        OauthClientDetailsModel oauthEntity = new OauthClientDetailsModel(userJson.getUsername(), BcryptUtil.getBcryptEncoder(userJson.getPassword()));
        this.oauthService.addClient(oauthEntity);
        UserBaseExtModel newUser = new UserBaseExtModel(userJson);
        this.userExtService.save(newUser);
        json.setData(newUser);
        return json;
    }

    /**
     * @author weilai
     * @date 2019年4月3日下午2:45:31
     * @desc <li>向cbm_mag_user_info 数据表增加记录
     */
    @GetMapping(value = "/login/white/getToken")
    public AjaxJson getToken(String username, String password) {
        AjaxJson json = new AjaxJson();
        try {
            String url = "http://127.0.0.1:8830/oauth/token";
            sendData(url, username, password, json);
        } catch (Exception e) {
            json.setMsg(e.getMessage());
            json.setCode(401);
        }
        return json;
    }


    public static void sendData(String url, String username, String password, AjaxJson json) throws IOException {
        String head = Base64codeUtil.getEncode(EncrypMD5Util.eccrypt(username), password);
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("username", username).add("password", password)
                .add("grant_type", "password").build();
        Request request = new Request.Builder().url(url).post(formBody).addHeader("Authorization", head).build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        //请求执行成功
        if (response.isSuccessful()) {
            //获取返回数据 可以是String，bytes ,byteStream
            String body = response.body().string();
            JWT javaObject = JSON.toJavaObject(JSON.parseObject(body), JWT.class);
            json.setData(javaObject);
        } else {
            json.setCode(401);
            json.setMsg("用户名或密码错误");
        }
    }


}
