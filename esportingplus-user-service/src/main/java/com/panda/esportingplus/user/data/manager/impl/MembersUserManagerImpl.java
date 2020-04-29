package com.panda.esportingplus.user.data.manager.impl;

import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.exception.BusinessException;
import com.panda.esportingplus.user.config.UserProperties;
import com.panda.esportingplus.user.constant.MembersAuthConstants;
import com.panda.esportingplus.user.data.manager.MembersUserManager;
import com.panda.esportingplus.user.domain.entity.MembersUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Auther: shusong.liang
 * @Date: 2020-03-31 17:41
 * @Description:
 */
@Service
public class MembersUserManagerImpl implements MembersUserManager {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserProperties userProperties;

    @Override
    public void checkSystemSwitch(MembersUser user, boolean forbiddenInWhiteMode) {
        if (userProperties.getUserSystemSwitch() == null) {//1.没有进行配置，不拦截
            return;
        } else if (userProperties.getUserSystemSwitch()
                == MembersAuthConstants.SYSTEM_CLOSE) {//2.系统关闭 不让登陆
            throw new BusinessException(BizExceptionEnum.USER_SYSTEM_MAINTAINING);
        } else if (userProperties.getUserSystemSwitch()
                == MembersAuthConstants.SYSTEM_WHITE_LIST) {//3.白名单模式下，白名单用户可登录，其他拦截
            if (user != null && user.getId() != null) {//白名单用户可以登录、测试账号可登陆
                if (userProperties.getTestPhone().contains(user.getPhone())) {
                    return;
                }
                throw new BusinessException(BizExceptionEnum.USER_SYSTEM_MAINTAINING);
            }
            if (forbiddenInWhiteMode) {
                throw new BusinessException(BizExceptionEnum.USER_SYSTEM_MAINTAINING);
            }
        }
        return;
    }
}