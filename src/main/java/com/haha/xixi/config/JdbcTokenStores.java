package com.haha.xixi.config;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author weilai
 * @date 2019/5/2916:01
 * @description: TODO
 */
public class JdbcTokenStores extends JdbcTokenStore {

    public JdbcTokenStores(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = null;

        try {
            accessToken = new DefaultOAuth2AccessToken(tokenValue);
        }
        catch (EmptyResultDataAccessException e) {

        }
        catch (IllegalArgumentException e) {

            removeAccessToken(tokenValue);
        }

        return accessToken;
    }
}
