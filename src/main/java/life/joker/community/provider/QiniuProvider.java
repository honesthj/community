package life.joker.community.provider;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import life.joker.community.exception.CustomizeErrorCode;
import life.joker.community.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author joker
 * @date 2023/03/11 11:52
 **/
@Component
public class QiniuProvider {
    @Value("${qiniu.accesskey}")
    private String accessKey;
    @Value("${qiniu.secretkey}")
    private String secretKey;
    @Value("${qiniu.bucketname}")
    private String bucket;
    @Value("${qiniu.domain}")
    private String domain;
    // 七牛文件上传管理器
    private UploadManager uploadManager;
    private String token;
    // 七牛认证管理
    private Auth auth;

    public String upload(InputStream file, String filename) {
        String generatedFilename;
        String[] filePaths = filename.split("\\.");
        if (filePaths.length > 1) {
            generatedFilename = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
        } else {
            return null;
        }
        // 构造一个带指定Region对象的配置类
        uploadManager = new UploadManager(new Configuration(Region.region0()));
        auth = Auth.create(accessKey, secretKey);
        // 根据命名空间生成的上传token
        token = auth.uploadToken(bucket);
        try {
            Response res = uploadManager.put(file, generatedFilename, token, null, null);
            if (!res.isOK()) {
                throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
            }
            // 解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(res.bodyString(), DefaultPutRet.class);
            String path = domain + "/" + putRet.key;
            // 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
            return path;
        } catch (QiniuException e) {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
    }
}