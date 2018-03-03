package cn.wolfcode.wms.qo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductStockQueryObject extends QueryObject {
    private String keywords;
    private long brandId = -1;
    private long depotId = -1;
    private BigDecimal limitNumber;

    public String getKeywords() {
        return empty2null(keywords);
    }
}
