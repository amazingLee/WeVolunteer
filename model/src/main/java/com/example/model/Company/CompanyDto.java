package com.example.model.Company;
/*
 *
 * Created by Ge on 2016/8/5.
 *
 */

import java.util.List;

public class CompanyDto {
    private int PageIndex;  // (integer, optional): 当前页码
    private int PageSize;   // (integer, optional): 每页条数
    private int TotalCount; // (integer, optional): 总共记录数
    private int TotalPages; // (integer, optional): 总共分页数
    private int StartPosition;  // (integer, optional): 记录开始位置
    private int EndPosition;    // (integer, optional): 记录结束位置
    private boolean HasPreviousPage;    // (boolean, optional): 是否有上一页
    private boolean HasNextPage;    // (boolean, optional): 是否有下一页
    private List<CompanyRowsDto> Rows;


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

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
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
