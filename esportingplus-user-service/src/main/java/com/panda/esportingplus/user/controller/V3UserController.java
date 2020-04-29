package com.panda.esportingplus.user.controller;

import com.panda.esportingplus.common.ResponsePacket;
import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.user.api.feign.V3UserServiceClient;
import com.panda.esportingplus.user.api.params.MiniprogramLoginParam;
import com.panda.esportingplus.user.api.params.MpPhoneBindLoginParam;
import com.panda.esportingplus.user.api.params.MpWxPhoneBindLoginParam;
import com.panda.esportingplus.user.api.params.PhoneRegistParam;
import com.panda.esportingplus.user.api.params.ThirdPartLoginParams;
import com.panda.esportingplus.user.api.params.UserPhoneLoginParams;
import com.panda.esportingplus.user.api.vo.MiniprogramLoginVo;
import com.panda.esportingplus.user.api.vo.PhoneRegistVo;
import com.panda.esportingplus.user.api.vo.ThirdpartLoginVo;
import com.panda.esportingplus.user.api.vo.UserPhoneLoginVo;
import com.panda.esportingplus.user.api.vo.UserPreRegistVo;

import com.panda.esportingplus.user.domain.service.RegistLoginService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: shusong.liang
 * @Date: 2020-03-26 15:10
 * @Description:
 */
@RestController
@RequestMapping("/auth")
@Api(tags = {"用户登陆服务接口"})
public class V3UserController implements V3UserServiceClient {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RegistLoginService registLoginService;

    /*@Autowired
    private AuthTokenService authTokenService;*/

    /**
     * 第三方注册登录接口
     *
     * @return token和融云token封装的vo
     */
    @PostMapping("/loginorregist")
    @Override
    public ResponsePacket<ThirdpartLoginVo> loginOrRegist(@RequestBody ThirdPartLoginParams params,
            @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
            @RequestParam(name = "x", required = false) String version) {
        return null;//registLoginService.loginOrRegist(params, mDeviceId, version);
    }

    /**
     * 手机注册接口
     */
    @PostMapping("/phone/regist")
    @Override
    public ResponsePacket<PhoneRegistVo> registPhone(@RequestBody PhoneRegistParam params,
            @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
            @RequestParam(name = "x", required = false) String version) {
        if (StringUtils.isBlank(params.getPhone())) {
            return ResponsePacket.onError(BizExceptionEnum.PARAM_ENTRY_ERROR);
        }
        if (StringUtils.isEmpty(params.getUsername())) {
            return ResponsePacket.onError(BizExceptionEnum.PARAM_ENTRY_ERROR);
        }
        if (params.getSex() == null) {
            return ResponsePacket.onError(BizExceptionEnum.PARAM_ENTRY_ERROR);
        }
        return null;//registLoginService.registPhone(params, mDeviceId, version);
    }

    /**
     * 手机注册接口
     */
    @PostMapping("/invit/preregist")
    @Override
    public ResponsePacket<UserPreRegistVo> invitPreRegist(@RequestBody PhoneRegistParam params) {
        if (StringUtils.isBlank(params.getPhone()) || params.getPhone().length() != 11) {
            return ResponsePacket.onError(BizExceptionEnum.PARAM_ENTRY_ERROR);
        }
        if (StringUtils.isBlank(params.getInvitingUid())) {
            return ResponsePacket.onError(BizExceptionEnum.PARAM_ENTRY_ERROR);
        }
        return null;//registLoginService.invitPreRegist(params);
    }


    /**
     * 手机登录接口
     */
    @PostMapping("/phone/login")
    @Override
    public ResponsePacket<UserPhoneLoginVo> loginPhone(@RequestBody UserPhoneLoginParams params,
            @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
            @RequestParam(name = "x", required = false) String version) {
        return registLoginService.loginPhone(null);
    }

    /**
     * 微信小程序unionid认证
     */
    @PostMapping("/mp/unionid/login")
    @Override
    public ResponsePacket<MiniprogramLoginVo> miniprogramUnionLogin(
            @RequestBody MiniprogramLoginParam params,
            @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
            @RequestParam(name = "x", required = false) String version) {
        return null;//registLoginService.miniprogramLogin(params, mDeviceId, version);
    }

    /**
     * 微信手机号码登录
     */
    @PostMapping("/mp/wxphone/login")
    @Override
    public ResponsePacket<MiniprogramLoginVo> mpWxPhoneBindLogin(
            @RequestBody MpWxPhoneBindLoginParam params,
            @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
            @RequestParam(name = "x", required = false) String version) {
        return null;//registLoginService.wxPhoneBindLogin(params, mDeviceId, version);
    }

    /**
     * 小程序自选手机号登录
     */
    @PostMapping("/mp/phone/login")
    @Override
    public ResponsePacket<MiniprogramLoginVo> mpPhoneBindLogin(
            @RequestBody MpPhoneBindLoginParam params,
            @RequestHeader(name = "HTTP-M-DEVICEID", required = false) String mDeviceId,
            @RequestParam(name = "x", required = false) String version) {
        return null;//registLoginService.phoneBindLogin(params, mDeviceId, version);
    }

    /**
     * 刷新token
     */
    @PostMapping("/logout")
    @Override
    public ResponsePacket<Void> logout(
            @RequestHeader(name = "Authorization", required = true) String pythonTohek,
            @RequestParam(name = "x", required = false) String version) {
        //authTokenService.removeAccessToken(pythonTohek, version);
        return ResponsePacket.onSuccess();
    }
}
