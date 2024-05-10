package com.raybyte.jstore.repository;

import com.raybyte.jstore.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface DetailRepository extends JpaRepository<Detail, Long>, JpaSpecificationExecutor<Detail> {

    boolean existsByDetailTypeAndDetailId(String detailType, String detailId);

    boolean existsByKeywordAndReadFlag(String keyword, Integer readFlag);

    Detail findByDetailTypeAndDetailId(String detailType, String detailId);

    @Transactional
    @Modifying
    @Query(value = "update detail set keyword =?2 , update_date =CURRENT_TIMESTAMP() where id = ?1",nativeQuery = true)
    int updateKeyword(Long id, String keyword);

    @Transactional
    @Modifying
    @Query(value = "update detail x set read_flag =3,update_date =CURRENT_TIMESTAMP() where read_flag =0 and keyword in (select keyword from (select keyword from detail d where read_flag=1 and keyword!='NONE') a)",nativeQuery = true)
    int markAllReadWithSameKeyword();
}
