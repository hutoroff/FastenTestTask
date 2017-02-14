package ru.hutoroff.fasten.testtask.service.data.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hutoroff on 06.02.17.
 */
public class SuccessResponseData {

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public SuccessResponseData(String apiToken, Date apiTokenExpirationDate) {
        this.apiToken = apiToken;
        this.apiTokenExpirationDate = df.format(apiTokenExpirationDate);
    }

    @JsonProperty("api_token")
    private String apiToken;
    @JsonProperty("api_token_expiration_date")
    private String apiTokenExpirationDate;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiTokenExpirationDate() {
        return apiTokenExpirationDate;
    }

    public void setApiTokenExpirationDate(String apiTokenExpirationDate) {
        this.apiTokenExpirationDate = apiTokenExpirationDate;
    }
}
