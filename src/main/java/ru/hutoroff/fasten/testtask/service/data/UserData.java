package ru.hutoroff.fasten.testtask.service.data;

import java.util.Objects;

/**
 * Created by hutoroff on 15.02.17.
 */
public class UserData {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserData)) return false;
        UserData userData = (UserData) o;
        return Objects.equals(getEmail(), userData.getEmail()) &&
                Objects.equals(getPassword(), userData.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "UserData{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
