package com.pc3r.vfarm.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link com.pc3r.vfarm.entities.Item}
 */
public class ItemDto implements Serializable {
    private final Integer id;
    @Size(max = 255)
    private final String name;
    @Size(max = 255)
    private final String type;
    private final Timestamp createdAt;
    private final Timestamp purchasedAt;
    private final Integer durability;
    private final BigDecimal value;
    private final UserDto owner;

    public ItemDto(Integer id, String name, String type, Timestamp createdAt, Timestamp purchasedAt, Integer durability, BigDecimal value,
                   UserDto owner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
        this.purchasedAt = purchasedAt;
        this.durability = durability;
        this.value = value;
        this.owner = owner;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getPurchasedAt() {
        return purchasedAt;
    }

    public Integer getDurability() {
        return durability;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto entity = (ItemDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.purchasedAt, entity.purchasedAt) &&
                Objects.equals(this.durability, entity.durability) &&
                Objects.equals(this.value, entity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, createdAt, purchasedAt, durability, value);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "type = " + type + ", " +
                "createdAt = " + createdAt + ", " +
                "purchasedAt = " + purchasedAt + ", " +
                "durability = " + durability + ", " +
                "value = " + value + ")";
    }

    public UserDto getOwner() {
        return owner;
    }
}