package com.panda.esportingplus.common.file;


import com.panda.esportingplus.common.config.QiNiuConfig;
import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.exception.BusinessException;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service("qiNiuFileUploadService")
@Configuration
@EnableConfigurationProperties({QiNiuConfig.class})
@ConditionalOnBean(QiNiuConfig.class)
public class QiNiuFileUploadServiceImpl implements FileUploadService, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiNiuFileUploadServiceImpl.class);
    @Autowired
    private QiNiuConfig qiNiuConfig;
    private Auth auth;
    private com.qiniu.storage.Configuration c;
    private UploadManager uploadManager;

    @Override
    public String upload(InputStream stream, String fileName) throws BusinessException {
        String serverFileName = null;
        try {
            serverFileName = convertFileName(fileName);
            if (checkFileInServer(serverFileName)) {
                return serverFileName;
            }
            //调用put方法上传
            uploadManager.put(stream, serverFileName, getUpToken(serverFileName), null, null);
        } catch (QiniuException e) {
            throw new BusinessException(BizExceptionEnum.FILE_UPLOAD_FAIL, fileName, e);
        } finally {
            closeStream(stream);
        }
        return serverFileName;
    }

    @Override
    public String upload(InputStream stream, String uploadFloder, String fileName, boolean forceRefresh)
            throws BusinessException {
        String serverFileName = null;
        try {
            serverFileName = convertFileName(uploadFloder, fileName);
            if (!forceRefresh && checkFileInServer(serverFileName)) {
                return serverFileName;
            }
            //调用put方法上传
            uploadManager.put(stream, serverFileName, getUpToken(serverFileName), null, null);
        } catch (QiniuException e) {
            throw new BusinessException(BizExceptionEnum.FILE_UPLOAD_FAIL, fileName, e);
        } finally {
            closeStream(stream);
        }
        return serverFileName;
    }

    @Override
    public String upload(InputStream stream, String env, String uploadFloder, String fileName,
                         boolean forceRefresh) throws BusinessException {
        String serverFileName = null;
        try {
            serverFileName = convertFileName(env, uploadFloder, fileName);
            if (!forceRefresh && checkFileInServer(serverFileName)) {
                return serverFileName;
            }
            //调用put方法上传
            uploadManager.put(stream, serverFileName, getUpToken(serverFileName), null, null);
        } catch (QiniuException e) {
            throw new BusinessException(BizExceptionEnum.FILE_UPLOAD_FAIL, fileName, e);
        } finally {
            closeStream(stream);
        }
        return serverFileName;
    }

    private String convertFileName(String env, String uploadFloder, String fileName) {
        return new StringBuilder("/").append(env).append(convertFileName(uploadFloder, fileName)).toString();
    }

    private String convertFileName(String uploadFloder, String fileName) {
        return new StringBuilder("/").append(uploadFloder).append("/").append(new SimpleDateFormat("yyyyMMdd").format(new Date())).append("/")
                .append(fileName).toString();
    }

    @Override
    public String upload(File file, String fileName) throws BusinessException {
        String serverFileName = null;
        try {
            serverFileName = convertFileName(fileName);
            //调用put方法上传
            uploadManager.put(file, serverFileName, getUpToken(serverFileName));
        } catch (QiniuException e) {
            throw new BusinessException(BizExceptionEnum.FILE_UPLOAD_FAIL, fileName, e);
        }
        return serverFileName;
    }

    @Override
    public String upload(String localFileName, String fileName) throws BusinessException {
        String serverFileName = null;
        try {
            serverFileName = convertFileName(fileName);
            //调用put方法上传
            uploadManager.put(localFileName, serverFileName, getUpToken(serverFileName));
        } catch (QiniuException e) {
            throw new BusinessException(BizExceptionEnum.FILE_UPLOAD_FAIL, localFileName, e);
        }
        return serverFileName;
    }

    private boolean checkFileInServer(String serverfileName) {
        BucketManager bucketManager = new BucketManager(auth, c);
        //检测文件是否存在
        boolean isExist = true;
        try {
            bucketManager.stat(qiNiuConfig.getBucketName(), serverfileName);
        } catch (QiniuException e) {
            // LOGGER.warn("file:{}not exist in qi niu", serverfileName);
            isExist = false;
        }
        return isExist;
    }

    @Override
    public String getUploadFileLink(String serverfileName) throws BusinessException {
        String fileLink = null;
        try {
            //检测文件是否存在
            fileLink =
                    qiNiuConfig.getBucketLink() + "/" + URLEncoder.encode(serverfileName, "utf-8");
            if (!qiNiuConfig.getBucketIsPub()) {
                fileLink = auth.privateDownloadUrl(fileLink, 3600) + "?v=" + System.currentTimeMillis();
            } else {
                fileLink = fileLink + "?v=" + System.currentTimeMillis();
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("URLEncoder encode failed");
        }
        return fileLink;
    }

    /**
     * 获取七牛token
     *
     * @param bucketName 空间名
     * @param key        文件名
     * @return
     * @throws BusinessException
     */
    @Override
    public String getUploadToken(String bucketName, String key) throws BusinessException {
        return auth.uploadToken(bucketName, key);
    }

    /**
     * 获取私密连接
     *
     * @param url 文件路径
     * @return
     */
    @Override
    public String privateDownloadUrl(String url) {
        String fileLink = auth.privateDownloadUrl(url, 86400);
        return fileLink;
    }


    /**
     * 获取七牛凭证
     *
     * @param bucketName 空间名称
     * @param key
     * @param policy
     * @return
     */
    public String getUploadTokenByPolicy(String bucketName, String key, StringMap policy) {
        //insertOnly 如果希望只能上传指定key的文件，并且不允许修改，那么可以将下面的 insertOnly 属性值设为 1
        return auth.uploadToken(bucketName, key, 3600, policy);
    }

    private String getUpToken(String fileName) {
        return auth.uploadToken(qiNiuConfig.getBucketName(), fileName);
    }

    private String convertFileName(String fileName) {
        return new StringBuilder("/").append(new SimpleDateFormat("yyyyMMdd").format(new Date())).append("/")
                .append(fileName).toString();
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
        c = new com.qiniu.storage.Configuration(Zone.autoZone());
        uploadManager = new UploadManager(c);
    }
}
