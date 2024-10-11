package com.raybyte.jstore.dto;

import lombok.Data;

@Data
public class MarkReadDTO {
    private String detailId;
    private String detailType;
    private Integer readFlag;
}
