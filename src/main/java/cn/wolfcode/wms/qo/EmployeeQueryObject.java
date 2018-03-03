package cn.wolfcode.wms.qo;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class EmployeeQueryObject extends QueryObject {
    private String keywords;
    private long deptId=-1;

    public String getKeywords() {
        return empty2null(keywords);
    }
}
