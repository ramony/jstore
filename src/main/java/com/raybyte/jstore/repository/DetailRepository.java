package com.raybyte.jstore.repository;

import com.raybyte.jstore.entity.Detail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface DetailRepository extends JpaRepository<Detail, Long>, JpaSpecificationExecutor<Detail> {

    boolean existsByDetailUrl(String detailUrl);

    boolean existsByKeywordAndReadFlag(String keyword, Integer readFlag);

    Detail findByDetailTypeAndDetailId(String detailType, Long detailId);

    Page<Detail> findByDetailTypeAndReadFlagAndDetailIdLessThan(String detailType, Integer readFlag, Long id, Pageable pageable);

    Page<Detail> findByDetailTypeAndTagIdAndReadFlagAndDetailIdLessThan(String detailType, Integer tagId, Integer readFlag, Long id, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update detail set keyword =?2 where id = ?1",nativeQuery = true)
    int updateKeyword(Long id, String keyword);

}
