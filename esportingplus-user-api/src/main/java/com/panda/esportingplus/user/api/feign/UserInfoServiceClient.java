package com.panda.esportingplus.user.api.feign;

import com.panda.esportingplus.common.ResponsePacket;
import com.panda.esportingplus.user.api.params.UserUpdateParams;

import com.panda.esportingplus.user.api.vo.UpdateUserAvatarResultVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;



/**
 * 用户基础服务Feign
 *
 * @zhangfang
 */
@FeignClient(name = "esportingplus-user-service", path = "/userinfo",
        fallbackFactory = UserInfoServiceClientFallbackFactory.class)
public interface UserInfoServiceClient {


    /**
     * 修改用户头像（用户头像审核）
     */
    @PostMapping("/update/avatar")
    public ResponsePacket<UpdateUserAvatarResultVo> updateAvatar(
            @RequestHeader("Authorization") String authorization,
            @RequestBody UserUpdateParams params);

    /**
     * 修改用户昵称
     */
    @PostMapping("/update/username")
    public ResponsePacket updateUsername(@RequestHeader("Authorization") String authorization,
            @RequestBody UserUpdateParams params);


}
