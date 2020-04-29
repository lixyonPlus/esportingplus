package com.panda.esportingplus.user.api.feign;

import com.panda.esportingplus.common.ResponsePacket;

import com.panda.esportingplus.user.api.params.UserUpdateParams;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


@Component
public class UserInfoServiceClientFallbackFactory implements
        FallbackFactory<UserInfoServiceClient> {

    @Override
    public UserInfoServiceClient create(Throwable throwable) {
        return new UserInfoServiceClient() {

            @Override
            public ResponsePacket updateAvatar(String authorization, UserUpdateParams params) {
                return ResponsePacket.onHystrix(throwable);
            }

            @Override
            public ResponsePacket updateUsername(String authorization, UserUpdateParams params) {
                return ResponsePacket.onHystrix(throwable);
            }

        };
    }
}
