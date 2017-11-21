package pers.mxy.data.transfer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 批量读取行情数据时使用的数据结构
 */
@Getter
@Setter
public class QuotationReadDto {
    private String nextCursor;
    private List<QuotationDto> quotationDtoList;
}
