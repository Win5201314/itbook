package com.zsl.boss.demo.config;

import com.zsl.boss.demo.util.MD5Util;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CredentialsMatcher extends SimpleCredentialsMatcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(CredentialsMatcher.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        Object tokenCredentials = MD5Util.generate(String.valueOf(authcToken.getPassword()));
        Object accountCredentials = getCredentials(info);
        return accountCredentials.equals(tokenCredentials);
    }
}
