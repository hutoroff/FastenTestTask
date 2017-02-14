package ru.hutoroff.fasten.testtask.service.data.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hutoroff on 06.02.17.
 */
public class ErrorResponseData implements ResponseData {

    @JsonProperty("error_description")
    private String errorDescription;
    @JsonProperty("error_code")
    private String errorCode;

    public ErrorResponseData(String errorDescription, String errorCode) {
        this.errorDescription = errorDescription;
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
