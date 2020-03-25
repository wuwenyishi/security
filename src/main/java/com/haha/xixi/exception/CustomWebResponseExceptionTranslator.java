package com.haha.xixi.exception;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @data 2018年11月4日 下午5:49:55
 * @desc 类描述
 * <li>
 */
@Component
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        String msg = null;
        Integer code = null;
        if (e instanceof ClientAbortException) {
            msg = e.getMessage();
            code = e.hashCode();

        } else if (e instanceof OAuth2Exception) {
            msg = e.getMessage();
            code = ((OAuth2Exception) e).getHttpErrorCode();
        }
        return ResponseEntity.status(code).body(new CustomOauthException(msg));
    }

}
