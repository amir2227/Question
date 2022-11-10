package com.mvp.question.payload.response.xapa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletAddressResponse {
    private Integer id;
    private XapaCointypes coinType;
    private String address;
    private String network;
    private String[] parameters;
    private String label;
    private Date createdAt;
}
