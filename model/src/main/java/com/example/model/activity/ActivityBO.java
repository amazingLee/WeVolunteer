package com.example.model.activity;

import java.util.List;

/**
 * 项目名称：BaseAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/7/23 15:48
 * 修改备注：
 */
public class ActivityBO {
    private static final String TAG = "ActivityBO";


    private int PageIndex;
    private int PageSize;
    private int TotalPages;
    private int StartPosition;
    private int EndPosition;
    private boolean HasPreviousPage;
    private boolean HasNextPage;
    private List<ActivityRowsBO> Rows;


    public List<ActivityRowsBO> getRows() {
        return Rows;
    }

    public void setRows(List<ActivityRowsBO> rows) {
        Rows = rows;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(int totalPages) {
        TotalPages = totalPages;
    }

    public int getStartPosition() {
        return StartPosition;
    }

    public void setStartPosition(int startPosition) {
        StartPosition = startPosition;
    }

    public int getEndPosition() {
        return EndPosition;
    }

    public void setEndPosition(int endPosition) {
        EndPosition = endPosition;
    }

    public boolean isHasPreviousPage() {
        return HasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        HasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return HasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        HasNextPage = hasNextPage;
    }
}
