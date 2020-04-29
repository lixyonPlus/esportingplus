package com.panda.esportingplus.common.constant;

import com.panda.esportingplus.common.tools.MacUtils;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("'${spring.profiles.active}'.equals('dev')")
public class DevelopAppTagGenerator implements AppTagGenerator {

    @Value("${spring.application.name}")
    private String appname;

    private String devTag;

    /**
     * 获取应用标识
     */
    @Override
    public String getApptag() {
        return appname + "-" + devTag;
    }

    @PostConstruct
    private void init() {
        appname = appname.substring(appname.indexOf('-') + 1);
        appname = appname.substring(0, appname.lastIndexOf('-'));

        devTag = MacUtils.getMac().replaceAll("-", "");
    }
}

