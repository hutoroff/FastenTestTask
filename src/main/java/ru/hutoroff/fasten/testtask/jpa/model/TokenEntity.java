package ru.hutoroff.fasten.testtask.jpa.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by hutoroff on 06.02.17.
 */
@Entity
@Table(name = "TOKEN")
public class TokenEntity {

    public TokenEntity() {
        this.creationDate = new Date();
    }

    @Id
    @Column(name = "TOKEN")
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIRATION_DATE", nullable = false)
    private Date expirationDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private UserEntity user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenEntity)) return false;
        TokenEntity that = (TokenEntity) o;
        return Objects.equals(getToken(), that.getToken()) &&
                Objects.equals(getExpirationDate(), that.getExpirationDate()) &&
                Objects.equals(getCreationDate(), that.getCreationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getExpirationDate(), getCreationDate());
    }

    @Override
    public String toString() {
        return "TokenEntity{" +
                "token='" + token + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
