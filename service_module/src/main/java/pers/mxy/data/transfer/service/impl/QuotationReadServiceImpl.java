package pers.mxy.data.transfer.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mxy.data.transfer.dao.QuotationDao;
import pers.mxy.data.transfer.dto.QuotationDto;
import pers.mxy.data.transfer.dto.QuotationReadDto;
import pers.mxy.data.transfer.service.QuotationReadService;
import redis.clients.jedis.ScanResult;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 读取行情数据的服务类
 */
@Service
public class QuotationReadServiceImpl implements QuotationReadService {

    @Autowired
    private QuotationDao quotationDao;

    @Autowired
    private Mapper mapper;

    /**
     * 取得行情数据
     */
    @Override
    public QuotationReadDto getQuotaionInfos(int offset) {
        QuotationReadDto quotationReadDto = new QuotationReadDto();
        int cursor = offset;
        ScanResult<String> scanResult = quotationDao.getQuotationKeysInfo(String.valueOf(cursor));
        Map<String, Map<String, String>> quoationInfoMaps = quotationDao.getQuotationInfos(scanResult.getResult());
        List<QuotationDto> quotationDtoList = new LinkedList<>();
        for (String key : quoationInfoMaps.keySet()) {
            quotationDtoList.add(mapper.map(quoationInfoMaps.get(key), QuotationDto.class));
        }
        quotationReadDto.setQuotationDtoList(quotationDtoList);
        quotationReadDto.setNextCursor(scanResult.getStringCursor());

        return quotationReadDto;
    }
}
