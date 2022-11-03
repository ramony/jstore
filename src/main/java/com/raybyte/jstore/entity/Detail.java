package com.raybyte.jstore.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name ="detail")
@Data
public class Detail {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "detail_type")
    private String detailType;

    @Column(name = "detail_id")
    private Long detailId;

    @Column(name = "detail_title")
    private String detailTitle;

    @Column(name = "detail_url")
    private String detailUrl;

    @Column(name = "read_flag")
    private Integer readFlag;

    @Column(name = "local_flag")
    private Integer localFlag;

    @Column(name = "page_no")
    private Long pageNo;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "update_date")
    private Timestamp updateDate;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "tag_id")
    private Integer tagId;
}
