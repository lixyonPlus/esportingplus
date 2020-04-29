package com.panda.esportingplus.user.data.manager;


import com.panda.esportingplus.user.domain.entity.MembersUser;

/**
 * @Auther: shusong.liang
 * @Date: 2020-03-31 17:41
 * @Description:
 */
public interface MembersUserManager {

    /**
     * 检查系统是否开启关闭模式、白名单模式
     * @param user 系统用户
     * @param forbiddenInWhiteMode
     * @return
     */
    void checkSystemSwitch(MembersUser user, boolean forbiddenInWhiteMode);


}
