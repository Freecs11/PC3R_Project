package com.pc3r.vfarm.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link com.pc3r.vfarm.entities.User}
 */
public class UserDto implements Serializable {
    private  Integer id;
    @Size(max = 255)
    private  String username;
    @Size(max = 255)
    private  String password;
    @Size(max = 255)
    private  String email;
    private Timestamp createdAt;
    @Size(max = 255)
    private  String position;
    @Size(max = 255)
    private  String role;
    private Integer coin;


    public UserDto(Integer id, String username, String password, String email, Timestamp createdAt, String position, String role , Integer coin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.position = position;
        this.role = role;
        this.coin = coin;
    }

    public UserDto() {

    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }


    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getPosition() {
        return position;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.username, entity.username) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.position, entity.position) &&
                Objects.equals(this.role, entity.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, createdAt, position, role);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "username = " + username + ", " +
                "password = " + password + ", " +
                "email = " + email + ", " +
                "createdAt = " + createdAt + ", " +
                "position = " + position + ", " +
                "role = " + role + ")";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreatedAt() {
        this.createdAt = Timestamp.from(Instant.now());
    }






}