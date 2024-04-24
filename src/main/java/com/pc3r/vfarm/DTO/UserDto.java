package com.pc3r.vfarm.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.pc3r.vfarm.entities.User}
 */
public class UserDto implements Serializable {
    private Integer id;
    @Size(max = 255)
    private String username;
    @Size(max = 255)
    private String password;
    @Size(max = 255)
    private String email;
    @Size(max = 255)
    private Integer coinId;
    private Instant createdAt;
    @Size(max = 255)
    private String position;
    @Size(max = 255)
    private String role;
    private Set<CoinDto> coins;
    private Set<ItemDto> items;
    private Set<LogDto> logs;
    private Set<PetDto> pets;

    public UserDto(Integer id, String username, String password, String email, Integer coinId, Instant createdAt, String position, String role,
                   Set<CoinDto> coins,
                   Set<ItemDto> items,
                   Set<LogDto> logs,
                   Set<PetDto> pets) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.coinId = coinId;
        this.createdAt = createdAt;
        this.position = position;
        this.role = role;
        this.coins = coins;
        this.items = items;
        this.logs = logs;
        this.pets = pets;
    }

    public UserDto() {

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

    public Integer getCoinId() {
        return coinId;
    }

    public Instant getCreatedAt() {
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
                Objects.equals(this.coinId, entity.coinId) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.position, entity.position) &&
                Objects.equals(this.role, entity.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, coinId, createdAt, position, role);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "username = " + username + ", " +
                "password = " + password + ", " +
                "email = " + email + ", " +
                "coinId = " + coinId + ", " +
                "createdAt = " + createdAt + ", " +
                "position = " + position + ", " +
                "role = " + role + ")";
    }

    public Set<CoinDto> getCoins() {
        return coins;
    }

    public Set<ItemDto> getItems() {
        return items;
    }

    public Set<LogDto> getLogs() {
        return logs;
    }

    public Set<PetDto> getPets() {
        return pets;
    }

    public void setCoins(Set<CoinDto> coins) {
        this.coins = coins;
    }

    public void setItems(Set<ItemDto> items) {
        this.items = items;
    }

    public void setLogs(Set<LogDto> logs) {
        this.logs = logs;
    }

    public void setPets(Set<PetDto> pets) {
        this.pets = pets;
    }

    // all setters
    public void setId(Integer id) {
        this.id = id;
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

    public void setCoinId(Integer coinId) {
        this.coinId = coinId;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toJson() {
        return "{\"id\": " + id + ", \"username\": \"" + username + "\", \"password\": \"" + password + "\", \"email\": \"" + email + "\", \"coinId\": \"" + coinId + "\", \"createdAt\": \"" + createdAt + "\", \"position\": \"" + position + "\", \"role\": \"" + role + "\"}";
    }


}