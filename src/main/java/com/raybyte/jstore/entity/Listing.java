package com.raybyte.jstore.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name ="listing")
@Data
public class Listing {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = " page_url")
    private String pageUrl;

}
