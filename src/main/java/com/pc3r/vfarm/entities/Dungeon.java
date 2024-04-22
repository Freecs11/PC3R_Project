package com.pc3r.vfarm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"DUNGEONS\"", schema = "public")
public class Dungeon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "\"LOCAL_X\"", nullable = false)
    private BigDecimal localX;

    @NotNull
    @Column(name = "\"LOCAL_Y\"", nullable = false)
    private BigDecimal localY;

    @NotNull
    @Column(name = "\"TIME\"", nullable = false)
    private Instant time;

    @OneToMany(mappedBy = "dungeon")
    private Set<DungeonTrait> dungeonTraits = new LinkedHashSet<>();

    @OneToMany(mappedBy = "dungeon")
    private Set<Reward> rewards = new LinkedHashSet<>();

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

    public Set<DungeonTrait> getDungeonTraits() {
        return dungeonTraits;
    }

    public void setDungeonTraits(Set<DungeonTrait> dungeonTraits) {
        this.dungeonTraits = dungeonTraits;
    }

    public Set<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(Set<Reward> rewards) {
        this.rewards = rewards;
    }

}