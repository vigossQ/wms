package cn.wolfcode.wms.qo;

import cn.wolfcode.wms.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class BillQueryObject extends QueryObject {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private int status = -1;

    public Date getEndDate() {
        if (endDate != null) {
            return DateUtil.getEndDate(endDate);
        } else {
            return null;
        }
    }
}
