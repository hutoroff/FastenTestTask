package ru.hutoroff.fasten.testtask.jpa.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by hutoroff on 06.02.17.
 */
@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    @SequenceGenerator(name = "USER_ID", sequenceName = "USER_ID")
    @GeneratedValue(generator = "USER_ID", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, unique = true)
    private Integer id;
    @Column(name = "LOGIN", unique = true, nullable = false)
    private String login;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    @OneToMany(mappedBy = "user"/*, fetch = FetchType.EAGER*/)
    private List<TokenEntity> tokens;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TokenEntity> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokenEntity> tokens) {
        this.tokens = tokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPassword(), getEmail());
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
