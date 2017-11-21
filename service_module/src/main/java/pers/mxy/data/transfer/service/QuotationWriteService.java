package pers.mxy.data.transfer.service;

import pers.mxy.data.transfer.dto.QuotationDto;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */
public interface QuotationWriteService {
    public void syncQuotationInfos(List<QuotationDto> quotationDtoList);
}
