package cn.wolfcode.wms.utils;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class PageResult {
    public static PageResult EMPTY_PAGE = new PageResult(1,1,1, Collections.EMPTY_LIST);
    private int currentPage;
    private int pageSize;

    private int totalCount;
    private List<?> data;

    private int endPage;
    private int prevPage;
    private int nextPage;

    public PageResult(int currentPage, int pageSize, int totalCount, List<?> data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;

        if (totalCount < pageSize) {
            endPage = 1;
            prevPage = 1;
            nextPage = 1;
        }

        endPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
        nextPage = currentPage + 1 < endPage ? currentPage + 1 : endPage;
    }
}
