package com.dongisarang.user.page;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude="pageList")
@Log
public class PageMaker<T> {

    private Page<T> result;

    private Pageable prevPage;
    private Pageable nextPage;

    private int currentPageNum;
    private int totalPageNum;

    private Pageable currentPage;

    private List<Pageable> pageList;

    public PageMaker(Page<T> result) {
        this.result = result;
        this.currentPage = result.getPageable();
        this.currentPageNum = currentPage.getPageNumber();
        this.totalPageNum = result.getTotalPages();
        this.pageList = new ArrayList<>();

        calcPages();
    }

    private void calcPages() {
        //TODO 페이지 계산 수정..
        int tempEndNum;
        int startNum;

        if(this.totalPageNum <= 5) {
            tempEndNum = totalPageNum - 1;
            startNum = 0;
        } else {
            tempEndNum = (int)(Math.ceil(this.currentPageNum/5.0)*5);
            startNum = tempEndNum - 4;
        }

        Pageable startPage = this.currentPage;

        for(int i = startNum; i < this.currentPageNum; i++) {
            startPage = startPage.previousOrFirst();
        }

        this.prevPage = startPage.getPageNumber() <= 0 ? null : startPage.previousOrFirst();

        log.info("tempEndNum: " + tempEndNum);
        log.info("total: " + totalPageNum);

        if(this.totalPageNum < tempEndNum) {
            tempEndNum = this.totalPageNum;
            this.nextPage = null;
        }

        for(int i = startNum; i <= tempEndNum; i++) {
            pageList.add(startPage);
            startPage = startPage.next();
            log.info("i : " + i);
        }

        this.nextPage = startPage.getPageNumber() + 1 < totalPageNum ? startPage : null;
    }
}
