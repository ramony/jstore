package com.raybyte.jstore.repository;

import com.raybyte.jstore.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ListingRepository extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {

    boolean existsByPageUrl(String pageUrl);

}
