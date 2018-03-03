package cn.wolfcode.wms.qo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQueryObject extends QueryObject {
    private String keywords;
    private long brandId = -1;

    public String getKeywords() {
        return empty2null(keywords);
    }
}
