package com.panda.esportingplus.common.tools;


import java.io.Closeable;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IO 操作类
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class IOUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOUtils.class);

    public static void close(Closeable... closeableList) {
        if (ObjectTools.isNotEmpty(closeableList)) {
            for (Closeable closeable : closeableList) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        LOGGER.error("流关闭失败", e);
                    }
                }
            }

        }

    }

}

