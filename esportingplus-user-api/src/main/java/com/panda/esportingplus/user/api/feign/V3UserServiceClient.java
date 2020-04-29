package com.panda.esportingplus.user.api.feign;

import com.panda.esportingplus.common.ResponsePacket;
import com.panda.esportingplus.user.api.vo.MiniprogramLoginVo;
import com.panda.esportingplus.user.api.vo.PhoneRegistVo;
import com.panda.esportingplus.user.api.vo.ThirdpartLoginVo;
import com.panda.esportingplus.user.api.vo.UserPhoneLoginVo;
import com.panda.esportingplus.user.api.vo.UserPreRegistVo;
import com.panda.esportingplus.user.api.params.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: shusong.liang
 * @Date: 2018-03-26 22:51
 * @Description: 用户服务接口
 */
@FeignClient(name = "esportingplus-user-service", path = "/auth", fallbackFactory = V3UserServiceClientFallbackFactory.class)
public interface V3UserServiceClient {

    /**
     * 第三方注册登录接口
     * @param params
     * @return token和融云token封装的vo
     */
    @PostMapping("/loginorregist")
    ResponsePacket<ThirdpartLoginVo> loginOrRegist(@RequestBody ThirdPartLoginParams params,
                                                   @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
                                                   @RequestParam(name = "x", required = false) String version);

    /**
     * 手机注册接口
     * @param params
     * @param mDeviceId
     * @param version
     * @return
     */
    @PostMapping("/phone/regist")
    ResponsePacket<PhoneRegistVo> registPhone(@RequestBody PhoneRegistParam params,
                                              @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
                                              @RequestParam(name = "x", required = false) String version);

    /**
     * 邀请注册接口
     */
    @PostMapping("/invit/preregist")
    ResponsePacket<UserPreRegistVo> invitPreRegist(@RequestBody PhoneRegistParam params);

    /**
     * 手机登录接口
     * @param params
     * @param mDeviceId
     * @param version
     * @return
     */
    @PostMapping("/phone/login")
    ResponsePacket<UserPhoneLoginVo> loginPhone(@RequestBody UserPhoneLoginParams params,
                                                @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
                                                @RequestParam(name = "x", required = false) String version);


    /**
     * 微信小程序unionid认证
     * @param params
     * @param mDeviceId
     * @param version
     * @return
     */
    @PostMapping("/mp/unionid/login")
    ResponsePacket<MiniprogramLoginVo> miniprogramUnionLogin(@RequestBody MiniprogramLoginParam params,
                                                             @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
                                                             @RequestParam(name = "x", required = false) String version);

    /**
     * 微信手机号码登录
     * @param params
     * @param mDeviceId
     * @param version
     * @return
     */
    @PostMapping("/mp/wxphone/login")
    ResponsePacket<MiniprogramLoginVo> mpWxPhoneBindLogin(@RequestBody MpWxPhoneBindLoginParam params,
            @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
            @RequestParam(name = "x", required = false) String version);


    /**
     * 小程序自选手机号登录
     * @param params
     * @param mDeviceId
     * @param version
     * @return
     */
    @PostMapping("/mp/phone/login")
    ResponsePacket<MiniprogramLoginVo> mpPhoneBindLogin(@RequestBody MpPhoneBindLoginParam params,
            @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
            @RequestParam(name = "x", required = false) String version);


    /**
     * 刷新token
     */
    @PostMapping("/logout")
    public ResponsePacket<Void> logout(
            @RequestHeader(name = "Authorization", required = true) String pythonTohek,
            @RequestParam(name = "x", required = false) String version);

}
