package com.raybyte.jstore.controller;

import com.raybyte.jstore.entity.Listing;
import com.raybyte.jstore.entity.Result;
import com.raybyte.jstore.repository.ListingRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/listing")
public class ListingController {
    Logger logger = Logger.getLogger(ListingController.class);

    @Autowired
    private ListingRepository listingRepository;

    @PostMapping("/createList")
    public Result create(@RequestBody Listing listing) {
        try {
            if(!listingRepository.existsByPageUrl(listing.getPageUrl())) {
                listingRepository.save(listing);
            }
            return Result.ok(1);
        }catch (Exception e) {
            logger.error("", e);
            return Result.fail("SAVE_ERROR_001",e.getMessage());
        }
    }

    @GetMapping("/exist")
    public Result exist(@Param("pageUrl") String pageUrl) {
        try {
            boolean exist = listingRepository.existsByPageUrl(pageUrl);
            return Result.ok(exist);
        }catch (Exception e) {
            logger.error("", e);
            return Result.fail("EXIST_ERROR_001",e.getMessage());
        }
    }

}
