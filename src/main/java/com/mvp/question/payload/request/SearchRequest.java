package com.mvp.question.payload.request;

import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchRequest {
    private String key;
    @Min(13)
    private Long startDate;
    @Min(13)
    private Long endDate;
}
