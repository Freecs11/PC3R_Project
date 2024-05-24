package com.pc3r.vfarm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "\"DUNGEON_TRAITS\"")
public class DungeonTrait {
    @Id
    @ColumnDefault("nextval('\"DUNGEON_TRAITS_ID_seq\"'")
    @Column(name = "\"ID\"", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"NAME\"", nullable = false)
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"DESCRIPTION\"", nullable = false)
    private String description;

    @NotNull
    @Column(name = "\"VALUE\"", nullable = false, precision = 38, scale = 2)
    private BigDecimal value;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"DUNGEON_ID\"", nullable = false)
    private Dungeon dungeon;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

}