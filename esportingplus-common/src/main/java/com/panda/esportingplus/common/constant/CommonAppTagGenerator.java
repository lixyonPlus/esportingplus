package com.panda.esportingplus.common.constant;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@ConditionalOnExpression("!'${spring.profiles.active}'.equals('dev')")
public class CommonAppTagGenerator implements AppTagGenerator {

    private String version = "v1";

    @Value("${spring.application.name}")
    private String appname;


    /**
     * 获取应用标识
     */
    @Override
    public String getApptag() {
        return appname + "-" + version;
    }


    @PostConstruct
    private void init() {
        appname = appname.substring(appname.indexOf('-') + 1);
        appname = appname.substring(0, appname.lastIndexOf('-'));
    }
}

