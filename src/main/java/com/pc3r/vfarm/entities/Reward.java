package com.pc3r.vfarm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "\"REWARDS\"")
public class Reward {
    @Id
    @ColumnDefault("nextval('\"REWARDS_ID_seq\"'")
    @Column(name = "\"ID\"", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"dungeonid\"", nullable = false)
    private Dungeon dungeon;


    @NotNull
    @Column(name = "\"VALUE\"", nullable = false, precision = 38, scale = 2)
    private BigDecimal value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }



    public BigDecimal getAmount() {
        return value;
    }

    public void setAmount(BigDecimal value) {
        this.value = value;
    }


}