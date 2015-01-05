package com.guangzhou.gov.net.bean;

/**
 * 用户信息
 * 
 * @ClassName: UserBean
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public class Userlogin extends BaseBean {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 8695612449602384208L;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String user_code;

    /**
     * 登陆成功标识：SUCCESS-成功 FAIL-失败
     */
    private String accet_code; //

    /**
     * 认证令牌
     */
    private String acess_token; //

    /**
     * 令牌有效期,单位：秒
     */
    private String expires_in; //

    /**
     * 用户信息
     */
    private UserInfo userInfo; //

    public String getAccet_code()
    {
        return accet_code;
    }

    public void setAccet_code(String accet_code)
    {
        this.accet_code = accet_code;
    }

    public String getAcess_token()
    {
        return acess_token;
    }

    public void setAcess_token(String acess_token)
    {
        this.acess_token = acess_token;
    }

    public String getExpires_in()
    {
        return expires_in;
    }

    public void setExpires_in(String expires_in)
    {
        this.expires_in = expires_in;
    }

    public UserInfo getUserInfo()
    {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo)
    {
        this.userInfo = userInfo;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUser_code()
    {
        return user_code;
    }

    public void setUser_code(String user_code)
    {
        this.user_code = user_code;
    }


}
