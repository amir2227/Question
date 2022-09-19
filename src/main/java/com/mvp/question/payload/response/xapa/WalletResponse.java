package com.mvp.question.payload.response.xapa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponse {
    private Integer id;
    private XapaCointypes coinType;
    private String name;
    private String network;
    private String balance;
    private String formatedBalance;
}
