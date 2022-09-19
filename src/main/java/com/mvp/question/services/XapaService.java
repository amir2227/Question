package com.mvp.question.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mvp.question.models.enums.CoinType;
import com.mvp.question.payload.response.xapa.LoginResponse;
import com.mvp.question.payload.response.xapa.WalletResponse;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

@Service
public class XapaService {

    @Value("${xapa.api}")
    private String xapaApi;
    @Value("${xapa.username}")
    private String username;
    @Value("${xapa.password}")
    private String password;

    private final String LOGIN_API = "login";
    private final String WALLET_API = "wallet/";
    private final String NEW_ADDRESS_API = "/address/new"; // WALLET_API/{wallet_id}/NEW_ADDRESS_API

    private final RestTemplate restTemplate = new RestTemplate();

    private String token;

    private final HttpHeaders getHeaders(){
        this.checktoken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-App-Version", "93847");
        headers.setBearerAuth(token);
        return headers;
    }

    public void login() {
        String url = xapaApi + LOGIN_API;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-App-Version", "73847");

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<LoginResponse> response = this.restTemplate.postForEntity(url, entity, LoginResponse.class);
        token = response.getBody().getToken();
    }

    public void checktoken() {
        if (token == null)
            this.login();
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        long exp = Long.valueOf(payload.split(",")[1].split(":")[1]);
        if ((System.currentTimeMillis() / 1000) > exp)
            this.login();
    }

    public Integer getWalletId(CoinType symbol) {
        this.checktoken();
        Integer wallet_id = 0;
        final String url = xapaApi + "wallets";
        HttpHeaders headers = this.getHeaders();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(null, headers);
        ResponseEntity<WalletResponse[]> response = this.restTemplate.exchange(url, HttpMethod.GET, entity,
                WalletResponse[].class);
        for (WalletResponse w : response.getBody()) {
            if (w.getCoinType().getSymbol().equals(symbol.toString())) {
                wallet_id = w.getId();
            }
        }

        return wallet_id;
    }

    public String generateNewAddress(CoinType symbol) {
        int wallet_id = this.getWalletId(symbol);
        String url = xapaApi + WALLET_API + wallet_id + NEW_ADDRESS_API;
        HttpHeaders headers = this.getHeaders();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(null, headers);
        return "r";
    }
}
