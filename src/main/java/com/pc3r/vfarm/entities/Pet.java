package com.pc3r.vfarm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"PETS\"", schema = "public")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ID\"", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "\"NAME\"", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "\"TYPE\"", nullable = false)
    private String type;

    @Column(name = "\"PURCHASED_AT\"", nullable = false)
    private Instant purchasedAt;

    @Column(name = "\"CREATED_AT\"", nullable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"OWNER_ID\"", nullable = false)
    private User owner;

    @Column(name = "\"HEALTH\"", nullable = false)
    private Integer health;

    @OneToMany(mappedBy = "pet")
    private Set<PetTrait> petTraits = new LinkedHashSet<>();

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

    public Instant getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(Instant purchasedAt) {
        this.purchasedAt = purchasedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Set<PetTrait> getPetTraits() {
        return petTraits;
    }

    public void setPetTraits(Set<PetTrait> petTraits) {
        this.petTraits = petTraits;
    }

}