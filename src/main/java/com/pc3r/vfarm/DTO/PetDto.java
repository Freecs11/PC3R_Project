package com.pc3r.vfarm.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.pc3r.vfarm.entities.Pet}
 */
public class PetDto implements Serializable {
    private final Integer id;
    @Size(max = 255)
    private final String name;
    @Size(max = 255)
    private final String type;
    private final Instant purchasedAt;
    private final Instant createdAt;
    private final Integer health;
    private final UserDto owner;
    private final Set<PetTraitDto> petTraits;

    public PetDto(Integer id, String name, String type, Instant purchasedAt, Instant createdAt, Integer health,
                  UserDto owner,
                  Set<PetTraitDto> petTraits) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.purchasedAt = purchasedAt;
        this.createdAt = createdAt;
        this.health = health;
        this.owner = owner;
        this.petTraits = petTraits;
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

    public Instant getPurchasedAt() {
        return purchasedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Integer getHealth() {
        return health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetDto entity = (PetDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.purchasedAt, entity.purchasedAt) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.health, entity.health);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, purchasedAt, createdAt, health);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "type = " + type + ", " +
                "purchasedAt = " + purchasedAt + ", " +
                "createdAt = " + createdAt + ", " +
                "health = " + health + ")";
    }

    public UserDto getOwner() {
        return owner;
    }

    public Set<PetTraitDto> getPetTraits() {
        return petTraits;
    }
}