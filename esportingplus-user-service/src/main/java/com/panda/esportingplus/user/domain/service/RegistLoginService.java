package com.panda.esportingplus.user.domain.service;


import com.panda.esportingplus.common.ResponsePacket;
import com.panda.esportingplus.user.api.params.PhoneRegistParam;
import com.panda.esportingplus.user.api.vo.PhoneLoginContext;
import com.panda.esportingplus.user.api.vo.PhoneRegistVo;
import com.panda.esportingplus.user.api.vo.UserPhoneLoginVo;


/**
 * @Auther: shusong.liang
 * @Date: 2020-03-31 10:53
 * @Description:
 */
public interface RegistLoginService {

    /**
     * 手机登录
     */
    ResponsePacket<UserPhoneLoginVo> loginPhone(PhoneLoginContext phoneLoginContext);

    /**
     * 手机注册方法
     */
    ResponsePacket<PhoneRegistVo> registPhone(PhoneRegistParam params, String mDevice,
                                              String version);


}
