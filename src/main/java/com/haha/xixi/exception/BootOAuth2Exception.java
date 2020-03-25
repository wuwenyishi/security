package com.haha.xixi.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author xuemd
 * @Date 2020/3/24
 * @description:
 **/
public class BootOAuth2Exception extends OAuth2Exception {

    public BootOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public BootOAuth2Exception(String msg) {
        super(msg);
    }

}
