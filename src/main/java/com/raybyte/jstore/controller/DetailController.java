package com.raybyte.jstore.controller;

import com.raybyte.jstore.dto.LinkDTO;
import com.raybyte.jstore.dto.LinksDTO;
import com.raybyte.jstore.dto.MarkReadDTO;
import com.raybyte.jstore.entity.Detail;
import com.raybyte.jstore.entity.Result;
import com.raybyte.jstore.repository.DetailRepository;
import com.raybyte.jstore.utils.PageUtils;
import org.apache.log4j.Logger;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detail")
public class DetailController {

    Logger logger = Logger.getLogger(DetailController.class);
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
            logger.error("", e);
            return Result.fail("SAVE_ERROR_001", e.getMessage());
        }
    }


    @RequestMapping("/queryByType/{type}")
    public Result queryByType(@Param("maxId") Long maxId, @PathVariable("type") String type) {
        if (maxId <= 0) {
            maxId = Long.MAX_VALUE;
        }
        try {
            Sort sort = Sort.by(Sort.Direction.DESC, "detailId");
            Page<Detail> pageList = detailRepository.findByDetailTypeAndReadFlagAndDetailIdLessThan(type, 0, maxId, PageUtils.create(1, 20, sort));
            LinksDTO links = new LinksDTO();
            if (!CollectionUtils.isEmpty(pageList.getContent())) {
                links.setList(pageList.getContent().stream().map(this::createLinkDTO).collect(Collectors.toList()));
                pageList.getContent().stream().map(Detail::getDetailId).reduce(Long::min).ifPresent(min -> links.setNext("?maxId=" + min));
            } else {
                links.setList(new ArrayList<>());
            }
            return Result.ok(links);
        } catch (Exception e) {
            logger.error("", e);
            return Result.fail("QUERY_ERROR_001", e.getMessage());
        }
    }

    @RequestMapping("/query/{limit}")
    public Result query(@PathVariable("limit") Integer limit) {
        try {
            List<Detail> pageList = detailRepository.findAll();
            if (limit > 0) {
                pageList = pageList.subList(0, limit);
            }
            pageList = pageList.stream().filter(d-> Objects.equals(d.getKeyword(),"NONE")).collect(Collectors.toList());
            return Result.ok(pageList);
        } catch (Exception e) {
            logger.error("", e);
            return Result.fail("QUERY_ERROR_001", e.getMessage());
        }
    }

    @RequestMapping("/checkKeywordRead/{keyword}")
    public Result checkKeywordRead(@PathVariable("keyword") String keyword) {
        try {
            boolean exist = detailRepository.existsByKeywordAndReadFlag(keyword, 1);
            return Result.ok(exist);
        }catch (Exception e) {
            logger.error("", e);
            return Result.fail("EXIST_ERROR_001",e.getMessage());
        }
    }

    @RequestMapping("/updateKeyword")
    public Result updateKeyword(@RequestBody List<Detail> list) {
        try {
            for(Detail detail: list) {
                int updateCount = detailRepository.updateKeyword(detail.getId(), detail.getKeyword());
            }
            return Result.ok(1);
        } catch (Exception e) {
            logger.error("", e);
            return Result.fail("UPDATE_ERROR_001", e.getMessage());
        }
    }

    @RequestMapping("/markReadByDetailId")
    public Result markReadByDetailId(@RequestBody MarkReadDTO markReadDTO) {
        try {
            // System.out.println(detailIdString);
            String[] details = markReadDTO.getDetailIdString().split("\\-");
            Detail detailDB = detailRepository.findByDetailTypeAndDetailId(details[0], Long.valueOf(details[1]));
            if (detailDB != null) {
                Integer readFlag = markReadDTO.getReadFlag();
                if (readFlag == null || readFlag < 1) {
                    readFlag = 1;
                }
                detailDB.setReadFlag(readFlag);
                detailDB.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                detailRepository.save(detailDB);
            }
            return Result.ok(1);
        } catch (Exception e) {
            logger.error("", e);
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
