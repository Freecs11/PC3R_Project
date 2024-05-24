package com.pc3r.vfarm.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "\"DUNGEONS\"")
public class Dungeon {
    @Id
    @ColumnDefault("nextval('\"DUNGEONS_ID_seq\"'")
    @Column(name = "\"ID\"", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"NAME\"", nullable = false)
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"TYPE\"", nullable = false)
    private String type;

    @NotNull
    @Column(name = "\"CREATED_AT\"", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "\"LOCAL_X\"", nullable = false, precision = 38, scale = 2)
    private BigDecimal localX;

    @NotNull
    @Column(name = "\"LOCAL_Y\"", nullable = false, precision = 38, scale = 2)
    private BigDecimal localY;

    @NotNull
    @Column(name = "\"TIME\"", nullable = false)
    private Instant time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getLocalX() {
        return localX;
    }

    public void setLocalX(BigDecimal localX) {
        this.localX = localX;
    }

    public BigDecimal getLocalY() {
        return localY;
    }

    public void setLocalY(BigDecimal localY) {
        this.localY = localY;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

}