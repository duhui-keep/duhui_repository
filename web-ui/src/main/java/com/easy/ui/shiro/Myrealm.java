package com.easy.ui.shiro;


import groovyjarjarantlr.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.codehaus.groovy.util.StringUtil;

public class Myrealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Object credentials = token.getCredentials();
        Object principal = token.getPrincipal();

        if (credentials!= null && principal != null ) {
            return new SimpleAuthenticationInfo(credentials, principal, getName());

        }

        return  null;

    }
}
