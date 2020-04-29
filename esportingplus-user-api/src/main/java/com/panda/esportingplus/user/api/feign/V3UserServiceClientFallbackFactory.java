package com.panda.esportingplus.user.api.feign;

import com.panda.esportingplus.common.ResponsePacket;
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
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: chen.junyong
 * @Date: 2018-09-12 10:50
 * @Description:
 */
@Component
public class V3UserServiceClientFallbackFactory implements FallbackFactory<V3UserServiceClient> {

    @Override
    public V3UserServiceClient create(Throwable cause) {
        return new V3UserServiceClient() {

            @Override
            public ResponsePacket<ThirdpartLoginVo> loginOrRegist(ThirdPartLoginParams params,
                    String mDeviceId, String version) {
                return ResponsePacket.onHystrix();
            }

            @Override
            public ResponsePacket<PhoneRegistVo> registPhone(PhoneRegistParam params,
                    String mDeviceId, String version) {
                return ResponsePacket.onHystrix();
            }

            @Override
            public ResponsePacket<UserPreRegistVo> invitPreRegist(PhoneRegistParam params) {
                return ResponsePacket.onHystrix();
            }

            @Override
            public ResponsePacket<UserPhoneLoginVo> loginPhone(UserPhoneLoginParams params,
                                                               String mDeviceId,
                                                               String version) {
                return ResponsePacket.onHystrix();
            }

            @Override
            public ResponsePacket<MiniprogramLoginVo> miniprogramUnionLogin(MiniprogramLoginParam params,
                    String mDeviceId,
                    String version) {
                return ResponsePacket.onHystrix();
            }

            @Override
            public ResponsePacket<MiniprogramLoginVo> mpWxPhoneBindLogin(
                    MpWxPhoneBindLoginParam params,
                    String mDeviceId, String version) {
                return ResponsePacket.onHystrix();
            }

            @Override
            public ResponsePacket<MiniprogramLoginVo> mpPhoneBindLogin(MpPhoneBindLoginParam params,
                    String mDeviceId, String version) {
                return ResponsePacket.onHystrix();
            }

            @Override
            public ResponsePacket<Void> logout(String pythonTohek, String version) {
                return ResponsePacket.onHystrix();
            }
        };
    }
}
