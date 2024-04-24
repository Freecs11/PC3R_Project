package com.pc3r.vfarm.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO for {@link com.pc3r.vfarm.entities.DungeonTrait}
 */
public class DungeonTraitDto implements Serializable {
    private final Integer id;
    @Size(max = 255)
    private final String name;
    @Size(max = 255)
    private final String description;
    private final BigDecimal value;
    private final DungeonDto dungeon;

    public DungeonTraitDto(Integer id, String name, String description, BigDecimal value,
                           DungeonDto dungeon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.dungeon = dungeon;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DungeonTraitDto entity = (DungeonTraitDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.value, entity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, value);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "value = " + value + ")";
    }

    public DungeonDto getDungeon() {
        return dungeon;
    }
}