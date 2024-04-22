package com.pc3r.vfarm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"USERS\"", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ID\"", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"USERNAME\"", nullable = false)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"PASSWORD\"", nullable = false)
    private String password;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"EMAIL\"", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"COIN_ID\"", nullable = false)
    private String coinId;

    @NotNull
    @Column(name = "\"CREATED_AT\"", nullable = false)
    private Instant createdAt;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"POSITION\"", nullable = false)
    private String position;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"ROLE\"", nullable = false)
    private String role;

    @OneToMany(mappedBy = "owner")
    private Set<Coin> coins = new LinkedHashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<Item> items = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Log> logs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<Pet> pets = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Coin> getCoins() {
        return coins;
    }

    public void setCoins(Set<Coin> coins) {
        this.coins = coins;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

}