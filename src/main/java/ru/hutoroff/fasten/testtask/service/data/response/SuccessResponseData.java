package ru.hutoroff.fasten.testtask.service.data.response;

import java.util.Date;

/**
 * Created by hutoroff on 06.02.17.
 */
public class SuccessResponseData {

    private String apiToken;
    private Date apiTokenExpirationDate;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Date getApiTokenExpirationDate() {
        return apiTokenExpirationDate;
    }

    public void setApiTokenExpirationDate(Date apiTokenExpirationDate) {
        this.apiTokenExpirationDate = apiTokenExpirationDate;
    }
}
