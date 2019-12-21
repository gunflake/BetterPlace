package com.dongisarang.user.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageVO {
    private static final int DEFAULT_SIZE = 10;

    private int page;

    private String keyword;
    private Integer type;

    public PageVO() {
        this.page = 1;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page < 0 ? 1 : page;
    }

    public int getSize() {
        return DEFAULT_SIZE;
    }

    public Pageable makePageable(String... props) {
        return PageRequest.of(this.page - 1, DEFAULT_SIZE, Sort.Direction.DESC, props);
    }

}
