package top.lumos04.hotel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
 
/**
 * Created with IntelliJ IDEA.
 *
 * @Project: aliyun
 * @Package: com.znsd.aliyun.alyunossfile.uilt
 * @Author: zhangrongjie
 * @Date: 2021/8/24 22:51
 * @Description:
 */
@Component //加入spring ioc 容器
@Data //get set 
@ConfigurationProperties(prefix = "aliyun.oss")	
public class OssConfig {
   private String endpoint; //外网访问 地域节点
   private String keyId;
   private String keySecret;
   private String bucketName; //kuctet 列表名称
}