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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detail")
public class DetailController {

    @Autowired
    private DetailRepository detailRepository;

    @RequestMapping("/create")
    public Result create(@RequestBody List<Detail> detailList) {
        try {
            for (Detail detail : detailList) {
                if (!detailRepository.existsByDetailUrl(detail.getDetailUrl())) {
                    detail.setCreateDate(new Timestamp(System.currentTimeMillis()));
                    detail.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                    detailRepository.save(detail);
                }
            }
            return Result.ok(1);
        } catch (Exception e) {
            return Result.fail("SAVE_ERROR_001", e.getMessage());
        }
    }

//    @RequestMapping("/query")
//    public Result query(@Param("pageNo") Integer pageNo) {
//        try {
//            Detail detail = new Detail();
//            detail.setReadFlag(0);
//            Example<Detail> example = Example.of(detail);
//            Sort sort = Sort.by(Sort.Direction.ASC, "pageNo");
//            Page<Detail> pageList = detailRepository.findAll(example, PageUtils.create(pageNo, 10, sort));
//            LinksDTO links = new LinksDTO();
//            if (pageList.getContent() != null) {
//                links.setList(pageList.getContent().stream().map(this::createLinkDTO).collect(Collectors.toList()));
//                if (!PageUtils.isLast(pageList)) {
//                    links.setNext("?pageNo=" + (PageUtils.get(pageList) + 1));
//                }
//            }
//            return Result.ok(links);
//        } catch (Exception e) {
//            return Result.fail("QUERY_ERROR_001", e.getMessage());
//        }
//    }

    @RequestMapping("/query")
    public Result query(@Param("maxId") Long maxId) {
        if (maxId <= 0) {
            maxId = Long.MAX_VALUE;
        }
        try {
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            Page<Detail> pageList = detailRepository.findByIdLessThanAndReadFlag(maxId, 0, PageUtils.create(1, 20, sort));
            LinksDTO links = new LinksDTO();
            if (!CollectionUtils.isEmpty(pageList.getContent())) {
                links.setList(pageList.getContent().stream().map(this::createLinkDTO).collect(Collectors.toList()));
                Optional<Long> minIdOpt = pageList.getContent().stream().map(Detail::getId).reduce(Long::min);
                if (minIdOpt.isPresent()) {
                    links.setNext("?maxId=" + minIdOpt.get());
                }
            } else {
                links.setList(List.of());
            }
            return Result.ok(links);
        } catch (Exception e) {
            return Result.fail("QUERY_ERROR_001", e.getMessage());
        }
    }

    @RequestMapping("/markReadByDetailId")
    public Result markReadByDetailId(@RequestBody Detail detail) {
        try {
            Detail detailDB = detailRepository.findByDetailId(detail.getDetailId());
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
