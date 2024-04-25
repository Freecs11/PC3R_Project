package com.pc3r.vfarm.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.pc3r.vfarm.entities.Dungeon}
 */
public class DungeonDto implements Serializable {
    private final Integer id;
    @Size(max = 255)
    private final String name;
    @Size(max = 255)
    private final String type;
    private final Instant createdAt;
    private final BigDecimal localX;
    private final BigDecimal localY;
    private final Instant time;
    private final Set<DungeonTraitDto> dungeonTraits;
    private final Set<RewardDto> rewards;

    public DungeonDto(Integer id, String name, String type, Instant createdAt, BigDecimal localX, BigDecimal localY, Instant time,
                      Set<DungeonTraitDto> dungeonTraits,
                      Set<RewardDto> rewards) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
        this.localX = localX;
        this.localY = localY;
        this.time = time;
        this.dungeonTraits = dungeonTraits;
        this.rewards = rewards;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getLocalX() {
        return localX;
    }

    public BigDecimal getLocalY() {
        return localY;
    }

    public Instant getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DungeonDto entity = (DungeonDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.localX, entity.localX) &&
                Objects.equals(this.localY, entity.localY) &&
                Objects.equals(this.time, entity.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, createdAt, localX, localY, time);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "type = " + type + ", " +
                "createdAt = " + createdAt + ", " +
                "localX = " + localX + ", " +
                "localY = " + localY + ", " +
                "time = " + time + ")";
    }

    public Set<DungeonTraitDto> getDungeonTraits() {
        return dungeonTraits;
    }

    public Set<RewardDto> getRewards() {
        return rewards;
    }
}