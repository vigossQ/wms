package cn.wolfcode.wms.qo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class QueryObject {
    private int currentPage = 1;
    private int pageSize = 3;

    public int getStart() {
        return (currentPage - 1) * pageSize;
    }

    protected String empty2null(String s) {
        return StringUtils.isEmpty(s) ? null : s;
    }
}
