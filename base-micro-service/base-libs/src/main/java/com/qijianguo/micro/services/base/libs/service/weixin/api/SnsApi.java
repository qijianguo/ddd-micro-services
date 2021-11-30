package com.qijianguo.micro.services.base.libs.service.weixin.api;

import com.qijianguo.micro.services.base.libs.service.weixin.bean.BaseResp;
import com.qijianguo.micro.services.base.libs.service.weixin.bean.UserInfo;
import com.qijianguo.micro.services.base.libs.service.weixin.bean.UserToken;
import com.qijianguo.micro.services.base.libs.util.HttpUtils;
import com.qijianguo.micro.services.base.libs.util.EmojiUtils;
import com.qijianguo.micro.services.base.libs.util.GsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qijianguo
 * 微信OAuth2.0授权登录让微信用户使用微信身份安全登录第三方应用或网站，在微信用户授权登录已接入微信OAuth2.0的第三方应用后，第三方可以获取到用户的
 * 接口调用凭证（access_token），通过access_token可以进行微信开放平台授权关系接口调用，从而可实现获取微信用户基本开放信息和帮助用户实现基础开放功能等。
 *
 * 微信OAuth2.0授权登录目前支持authorization_code模式，适用于拥有server端的应用授权。
 * 调用流程：
 * 1. 第三方发起微信授权登录请求，微信用户允许授权第三方应用后，微信会拉起应用或重定向到第三方网站，并且带上授权临时票据code参数；
 * 2. 通过code参数加上AppID和AppSecret等，通过API换取access_token；
 * 3. 通过access_token进行接口调用，获取用户基本数据资源或帮助用户实现基本操作。
 */
public class SnsApi {

    /** 通过code获取access_token */
    private static final String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /** 检查access_token有效性 */
    private static final String AUTH = "https://api.weixin.qq.com/sns/auth";
    /** 刷新access_token */
    private static final String REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    /** 获取用户个人信息 */
    private static final String USER_INFO = "https://api.weixin.qq.com/sns/userinfo";

    private static final String WIN_XIN_CODE = "0";
    private static final String WIN_XIN_MSG = "ok";

    /**
     * 通过code获取access_token
     * @param appId 应用唯一标识，在微信开放平台提交应用审核通过后获得
     * @param secret 应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
     * @param code 用户授权后传递的code参数
     * @return access_token
     */
    public static UserToken oauth2AccessToken(String appId, String secret, String code) throws Exception {
        Map<String, String> map = new HashMap(4) {{
            put("appid", appId); put("secret", secret); put("code", code); put("grant_type", "authorization_code");
        }};
        String s = HttpUtils.get(ACCESS_TOKEN, map);
        return GsonUtils.convertString2Object(s, UserToken.class);
    }

    /**
     * 检查access_token有效性
     * @param accessToken 接口调用凭证
     * @param openId 授权用户唯一标识
     * @return 校验结果，code=0，msg=ok为成功
     */
    public static BaseResp auth(String accessToken, String openId) throws Exception {
        Map<String, String> map = new HashMap(2) {{
            put("openid", openId); put("access_token", accessToken);
        }};
        String s = HttpUtils.get(AUTH, map);
        BaseResp baseResp = GsonUtils.convertString2Object(s, BaseResp.class);
        validate(baseResp);
        return baseResp;
    }

    /**
     * 刷新access_token有效期
     * @param appId 应用唯一标识，在微信开放平台提交应用审核通过后获得
     * @param refreshToken oauth2AccessToken获取到的refresh_token参数
     * @return 用户TOKEN
     */
    public static UserToken oauth2RefreshToken(String appId, String refreshToken) throws Exception {
        HashMap map = new HashMap(3) {{
            put("appid", appId);
            put("refresh_token", refreshToken);
            put("grant_type", "refresh_token");
        }};
        String s = HttpUtils.get(REFRESH_TOKEN, map);
        return GsonUtils.convertString2Object(s, UserToken.class);
    }

    /**
     * 获取用户个人信息
     * @param accessToken 接口调用凭证
     * @param openId 授权用户唯一标识
     * @param lang zh-CN
     * @return 用户个人信息
     */
    public static UserInfo userInfo(String accessToken, String openId, String lang) throws Exception {
        return userInfo(accessToken, openId, lang, 0);
    }

    /**
     * 获取用户个人信息
     * @param accessToken 接口调用凭证
     * @param openId 授权用户唯一标识
     * @param lang zh-CN
     * @param emoji 昵称表情符替换，0不替换，1替换
     * @return 用户个人信息
     */
    public static UserInfo userInfo(String accessToken, String openId, String lang, int emoji) throws Exception {
        HashMap map = new HashMap(3) {{
            put("openid", openId); put("access_token", accessToken); put("lang", lang);
        }};
        String s = HttpUtils.get(USER_INFO, map);
        UserInfo userInfo = GsonUtils.convertString2Object(s, UserInfo.class);
        if (emoji != 0 && userInfo != null && userInfo.getNickname() != null) {
            userInfo.setNickname_emoji(EmojiUtils.parse(userInfo.getNickname(), emoji));
        }
        return userInfo;
    }

    private static void validate(BaseResp baseResult) {
        if (!WIN_XIN_CODE.equals(baseResult.getErrcode()) || !WIN_XIN_MSG.equals(baseResult.getErrmsg())) {
            throw new RuntimeException("SnsApi.validate error: " + baseResult.toString());
        }
    }

}
