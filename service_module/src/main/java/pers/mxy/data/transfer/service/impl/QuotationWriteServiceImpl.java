package pers.mxy.data.transfer.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mxy.data.transfer.common.SystemProperties;
import pers.mxy.data.transfer.dao.QuotationDao;
import pers.mxy.data.transfer.dto.QuotationDto;
import pers.mxy.data.transfer.service.QuotationWriteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将行情数据同步到本地缓存
 */
@Service
public class QuotationWriteServiceImpl implements QuotationWriteService {
    @Autowired
    private QuotationDao quotationDao;

    @Autowired
    private Mapper mapper;

    @Autowired
    private SystemProperties systemProperties;

    /**
     * 将行情数据同步到本地缓存
     */
    @Override
    public void syncQuotationInfos(List<QuotationDto> quotationDtoList) {
        Map<String, Map<String, String>> quotationMap = new HashMap<>();
        for (QuotationDto quotationDto : quotationDtoList) {
            quotationMap.put(systemProperties.getQuotationBackHashKey() + quotationDto.getCode(),
                    mapper.map(quotationDto, HashMap.class));
        }
        quotationDao.setQuotationInfos(quotationMap);
        //quotationDao.setQuotationInfo(quotationDto.getCode(), mapper.map(quotationDto, HashMap.class));
    }
}
