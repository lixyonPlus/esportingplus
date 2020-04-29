package com.panda.esportingplus.user.domain.service.impl;




import com.panda.esportingplus.common.ResponsePacket;
import com.panda.esportingplus.user.api.params.PhoneRegistParam;
import com.panda.esportingplus.user.api.vo.PhoneLoginContext;
import com.panda.esportingplus.user.api.vo.PhoneRegistVo;
import com.panda.esportingplus.user.api.vo.UserPhoneLoginVo;
import com.panda.esportingplus.user.data.manager.MembersUserManager;
import com.panda.esportingplus.user.domain.service.RegistLoginService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Auther: shusong.liang
 * @Date: 2018-10-24 10:54
 * @Description:
 */
@Service
public class RegistLoginServiceImpl implements RegistLoginService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MembersUserManager membersUserManager;

    /**
     * 手机登录
     */
    @Override
    public ResponsePacket<UserPhoneLoginVo> loginPhone(PhoneLoginContext phoneLoginContext) {

        return null;
    }

    @Override
    public ResponsePacket<PhoneRegistVo> registPhone(PhoneRegistParam params, String mDevice, String version) {
        return null;
    }
}
