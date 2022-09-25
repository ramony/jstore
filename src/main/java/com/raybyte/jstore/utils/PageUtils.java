package com.raybyte.jstore.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtils {

    public static Pageable create(Integer pageNo, int pageSize, Sort sort) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return pageable;
    }

    public static Integer get(Page page) {
        return page.getPageable().getPageNumber() + 1;
    }

    public static boolean isLast(Page page) {
        return page.getPageable().getPageNumber() + 1 >= page.getTotalPages();
    }

}
