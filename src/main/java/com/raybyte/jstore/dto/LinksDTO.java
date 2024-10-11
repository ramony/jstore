package com.raybyte.jstore.dto;

import lombok.Data;

import java.util.List;

@Data
public class LinksDTO {
    private List<LinkDTO> list;
    private String next;
    private Integer totalPages;
}
