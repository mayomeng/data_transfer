package pers.mxy.data.transfer.publish;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.mxy.data.transfer.dto.QuotationDto;
import pers.mxy.data.transfer.dto.QuotationReadDto;
import pers.mxy.data.transfer.mq.rabbitmq.Producer;
import pers.mxy.data.transfer.service.QuotationReadService;

import java.util.LinkedList;
import java.util.List;

/**
 * 从本地缓存中取得行情数据，发布到消息队列中
 */
@RestController
@Slf4j
public class PublishAction {
    @Autowired
    private QuotationReadService quotationReadService;

    @Autowired
    private Producer producer;

    @RequestMapping(value = "/quotation", method = RequestMethod.GET)
    public List<QuotationDto> getQuotation() {
        return getAllList();
    }

    /**
     * 从缓存中迭代的取得行情数据
     */
    private List<QuotationDto> getAllList() {
        List<QuotationDto> quotationDtoList = new LinkedList<>();
        int offset = 0;
        while (true) {
            QuotationReadDto quotationReadDto = quotationReadService.getQuotaionInfos(offset);
            sendQuotationMessage(quotationReadDto.getQuotationDtoList());
            quotationDtoList.addAll(quotationReadDto.getQuotationDtoList());
            if ("0".equals(quotationReadDto.getNextCursor())) {
                break;
            }
            offset = Integer.parseInt(quotationReadDto.getNextCursor());
        }
        return quotationDtoList;
    }

    /**
     * 将行情数据发送给消息队列
     */
    private void sendQuotationMessage(List<QuotationDto> quotationDtoList) {
        for (QuotationDto quotationDto : quotationDtoList) {
            producer.sendMessage(JSON.toJSONString(quotationDto));
        }
    }
}
