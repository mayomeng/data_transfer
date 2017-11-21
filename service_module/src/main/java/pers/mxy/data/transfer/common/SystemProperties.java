package pers.mxy.data.transfer.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 个性化设置配置信息
 */
@ConfigurationProperties(prefix = "system")
@PropertySource("classpath:system.properties")
@Component
@Getter
@Setter
public class SystemProperties {
    // 无锡行情数据redis节点url
    private String quotationUrl;
    // 沈阳行情数据redis节点url
    private String quotationBackUrl;
    // 每次批量读/写数据的条数
    private int batchCount;
    // 无锡行情数据的hashkey
    private String quotationHashKey;
    // 沈阳行情数据的hashkey
    private String quotationBackHashKey;
}
