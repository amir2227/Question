package com.mvp.question.payload.response.xapa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XapaCointypes {
    private Integer id;
    private String name;
    private String symbol;
    private String blockchain;
    private String formattedFiatExchangeRate;
    
}
