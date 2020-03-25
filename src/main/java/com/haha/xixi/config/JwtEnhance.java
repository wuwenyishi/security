package com.haha.xixi.config;

import com.google.common.collect.Maps;
import com.haha.xixi.model.UserBaseModel;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * @author admin
 * @Date 2020/3/25
 * @description:
 **/
public class JwtEnhance implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> map = Maps.newHashMap();
        UserBaseModel user = (UserBaseModel)authentication.getPrincipal();
        map.put("userId", user.getId());
        map.put("loginName",user.getUsername());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
        return accessToken;
    }


}
