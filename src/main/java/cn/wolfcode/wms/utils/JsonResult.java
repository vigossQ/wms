package cn.wolfcode.wms.utils;

import lombok.Getter;

@Getter
public class JsonResult {
    private boolean success = true;
    private String errorMsg;

    public void mark(String errorMsg) {
        this.success = false;
        this.errorMsg = errorMsg;
    }
}
