package ru.hutoroff.fasten.testtask.service.data.response;

/**
 * Created by hutoroff on 06.02.17.
 */
public class ErrorResponseData implements ResponseData {

    private String errorDescription;
    private String error_code;

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}
