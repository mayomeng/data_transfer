package pers.mxy.data.transfer.service;

import pers.mxy.data.transfer.dto.QuotationReadDto;

/**
 * Created by Administrator on 2017/11/6.
 */
public interface QuotationReadService {
    public QuotationReadDto getQuotaionInfos(int offset);
}
