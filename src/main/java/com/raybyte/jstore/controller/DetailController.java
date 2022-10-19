package com.raybyte.jstore.controller;

import com.raybyte.jstore.dto.LinkDTO;
import com.raybyte.jstore.dto.LinksDTO;
import com.raybyte.jstore.entity.Detail;
import com.raybyte.jstore.entity.Result;
import com.raybyte.jstore.repository.DetailRepository;
import com.raybyte.jstore.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detail")
public class DetailController {

    @Autowired
    private DetailRepository detailRepository;

    @RequestMapping("/createDetail")
    public Result<Integer> create(@RequestBody List<Detail> detailList) {
        int count = 0;
        try {
            for (Detail detail : detailList) {
                if (!detailRepository.existsByDetailUrl(detail.getDetailUrl())) {
                    detail.setCreateDate(new Timestamp(System.currentTimeMillis()));
                    detail.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                    detailRepository.save(detail);
                    count++;
                }
            }
            return Result.ok(count);
        } catch (Exception e) {
            return Result.fail("SAVE_ERROR_001", e.getMessage());
        }
    }


    @RequestMapping("/query/{source}")
    public Result queryMax(@Param("maxId") Long maxId, @PathVariable("source") String source) {
        if (maxId <= 0) {
            maxId = Long.MAX_VALUE;
        }
        try {
            Sort sort = Sort.by(Sort.Direction.DESC, "detailId");
            Page<Detail> pageList = detailRepository.findByDetailTypeAndReadFlagAndDetailIdLessThan(source, 0, maxId, PageUtils.create(1, 20, sort));
            LinksDTO links = new LinksDTO();
            if (!CollectionUtils.isEmpty(pageList.getContent())) {
                links.setList(pageList.getContent().stream().map(this::createLinkDTO).collect(Collectors.toList()));
                pageList.getContent().stream().map(Detail::getDetailId).reduce(Long::min).ifPresent(min -> links.setNext("?maxId=" + min));
            } else {
                links.setList(new ArrayList<>());
            }
            return Result.ok(links);
        } catch (Exception e) {
            return Result.fail("QUERY_ERROR_001", e.getMessage());
        }
    }

    @RequestMapping("/markReadByDetailId")
    public Result markReadByDetailId(@RequestBody String detailIdString) {
        try {
            String[] details = detailIdString.split("\\-");
            Detail detailDB = detailRepository.findByDetailTypeAndDetailId(details[0], Long.valueOf(details[1]));
            if (detailDB != null) {
                detailDB.setReadFlag(1);
                detailDB.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                detailRepository.save(detailDB);
            }
            return Result.ok(1);
        } catch (Exception e) {
            return Result.fail("MARK_ERROR_001", e.getMessage());
        }
    }

    private LinkDTO createLinkDTO(Detail detail) {
        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setTitle("[" + detail.getPageNo() + "]" + detail.getDetailTitle());
        linkDTO.setUrl(detail.getDetailUrl());
        return linkDTO;
    }

}
