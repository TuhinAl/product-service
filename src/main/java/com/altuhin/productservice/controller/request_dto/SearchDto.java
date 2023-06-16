package com.altuhin.productservice.controller.request_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SearchDto {
    public Integer page;
    public Integer size = 10;
    public String multiSearchProperty;
}
