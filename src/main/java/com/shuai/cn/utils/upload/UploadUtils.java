package com.shuai.cn.utils.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@Component
public class UploadUtils {

    @Value("${alibabaupload.accessKeyId}")
    private String accessKeyId;

    @Value("${alibabaupload.endpoint}")
    private String endpoint;

    @Value("${alibabaupload.accessKeySecret}")
    private String accessKeySecret;

    @Value("${alibabaupload.url}")
    private String url;

    public String upload(Part part) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
//        String endpoint = "yourEndpoint";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKeyId = "yourAccessKeyId";
//        String accessKeySecret = "yourAccessKeySecret";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写网络流地址。
        long l = System.nanoTime();
        String fileName = l + "." + StringUtils.getFilenameExtension(part.getSubmittedFileName());
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        InputStream inputStream = null;
        try {
            inputStream = part.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.putObject("wwwshuai", fileName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url+fileName;
    }

}
