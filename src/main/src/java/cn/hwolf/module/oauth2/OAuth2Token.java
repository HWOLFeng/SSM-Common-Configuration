package cn.hwolf.module.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author hwolf
 * @email h.wolf@qq.com
 * @date 2017/11/3.
 */
public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

