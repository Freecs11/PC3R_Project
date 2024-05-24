package com.pc3r.vfarm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import java.sql.Timestamp;


@Entity
@Table(name = "\"USERS\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "\"ID\"", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "\"USERNAME\"", nullable = false)
    private String username;

    @Size(max = 255)
    @Column(name = "\"PASSWORD\"", nullable = false)
    private String password;

    @Size(max = 255)
    @Column(name = "\"EMAIL\"", nullable = false)
    private String email;

    @Column(name = "\"CREATED_AT\"", nullable = false)
    private Timestamp  createdAt;

    @Size(max = 255)
    @Column(name = "\"POSITION\"", nullable = false)
    private String position;

    @Size(max = 255)
    @Column(name = "\"ROLE\"", nullable = false)
    private String role;

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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
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

}