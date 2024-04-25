package com.pc3r.vfarm.DTO;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO for {@link com.pc3r.vfarm.entities.Reward}
 */
public class RewardDto implements Serializable {
    private final Integer id;
    private final BigDecimal value;
    private final DungeonDto dungeon;
    private final CoinDto coin;

    public RewardDto(Integer id, BigDecimal value,
                     DungeonDto dungeon,
                     CoinDto coin) {
        this.id = id;
        this.value = value;
        this.dungeon = dungeon;
        this.coin = coin;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RewardDto entity = (RewardDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.value, entity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "value = " + value + ")";
    }

    public DungeonDto getDungeon() {
        return dungeon;
    }

    public CoinDto getCoin() {
        return coin;
    }
}