package pers.mxy.data.transfer.subscribe;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.mxy.data.transfer.common.SystemProperties;
import pers.mxy.data.transfer.dto.QuotationDto;
import pers.mxy.data.transfer.mq.rabbitmq.Consumer;
import pers.mxy.data.transfer.service.QuotationWriteService;

import java.util.LinkedList;
import java.util.List;

/**
 * 订阅行情数据，将取得的行情数据吸入到本地缓存
 */
@Component
public class SubscribeAction implements Consumer {

    @Autowired
    private SystemProperties systemProperties;

    @Autowired
    private QuotationWriteService quotationWriteService;

    private List<QuotationDto> quotationDtoList = new LinkedList<>();

    @RabbitListener(queues = "${rabbitmq.queueName}")
    @Override
    public void handleMessage(String message) throws Exception {
        QuotationDto quotationDto = JSON.parseObject(message, QuotationDto.class);
        quotationDtoList.add(quotationDto);
        //System.out.println(Thread.currentThread().getName() + " : " + message);
        if (quotationDtoList.size() == systemProperties.getBatchCount()) {
            quotationWriteService.syncQuotationInfos(quotationDtoList);
            quotationDtoList.clear();
        }
    }
}
