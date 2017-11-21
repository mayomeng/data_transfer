package pers.mxy.data.transfer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 行情数据结构
 */
@Getter
@Setter
public class QuotationDto {
    private String code; // 股票代码
    private String name; // 股票名称
    private String openingPrice; // 开盘价
    private String closingPrice; // 收盘价
    private String maxPrice; // 最高价
    private String minPrice; // 最低价
    private String riseRange; // 涨幅
    private String dropRange; // 跌幅
}
