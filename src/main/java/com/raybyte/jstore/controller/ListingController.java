package com.raybyte.jstore.controller;

import com.raybyte.jstore.entity.Listing;
import com.raybyte.jstore.entity.Result;
import com.raybyte.jstore.repository.ListingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listing")
public class ListingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListingController.class);

    @Autowired
    private ListingRepository listingRepository;

    @RequestMapping("/createList")
    public Result create(@RequestBody Listing listing) {
        try {
            if(!listingRepository.existsByPageUrl(listing.getPageUrl())) {
                listingRepository.save(listing);
            }
            return Result.ok(1);
        }catch (Exception e) {
            LOGGER.error("", e);
            return Result.fail("SAVE_ERROR_001",e.getMessage());
        }
    }

    @RequestMapping("/exist")
    public Result exist(@Param("pageUrl") String pageUrl) {
        try {
            boolean exist = listingRepository.existsByPageUrl(pageUrl);
            return Result.ok(exist);
        }catch (Exception e) {
            LOGGER.error("", e);
            return Result.fail("EXIST_ERROR_001",e.getMessage());
        }
    }

}
