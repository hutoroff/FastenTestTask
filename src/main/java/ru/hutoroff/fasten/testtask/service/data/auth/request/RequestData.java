package ru.hutoroff.fasten.testtask.service.data.auth.request;

import java.io.Serializable;

/**
 * Created by hutoroff on 06.02.17.
 */
public class RequestData implements Serializable {

    static final long serialVersionUID = 1L;

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
