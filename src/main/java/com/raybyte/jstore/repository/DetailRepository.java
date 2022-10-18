package com.raybyte.jstore.repository;

import com.raybyte.jstore.entity.Detail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetailRepository extends JpaRepository<Detail, Long>, JpaSpecificationExecutor<Detail> {

    boolean existsByDetailUrl(String detailUrl);

    Detail findByDetailTypeAndDetailId(String detailType, Long detailId);

    Page<Detail> findByDetailTypeAndReadFlagAndDetailIdLessThan(String detailType, Integer readFlag, Long id, Pageable pageable);

}
