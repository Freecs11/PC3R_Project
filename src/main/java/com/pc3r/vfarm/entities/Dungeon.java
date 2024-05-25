package com.pc3r.vfarm.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "\"DUNGEONS\"")
public class Dungeon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dungeon_seq")
    @Column(name = "\"ID\"", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "\"NAME\"", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "\"TYPE\"", nullable = false)
    private String type;

    @Column(name = "\"CREATED_AT\"", nullable = false)
    private Timestamp createdAt;

    @Column(name = "\"LOCAL_X\"", nullable = false, precision = 38, scale = 2)
    private BigDecimal localX;

    @Column(name = "\"LOCAL_Y\"", nullable = false, precision = 38, scale = 2)
    private BigDecimal localY;

    @Column(name = "\"TIME\"", nullable = false)
    private Timestamp time;

    @Column(name = "\"status\"")
    private String status;

    @Column(name = "\"selected_items\"")
    private String selectedItems;

    @Column(name = "\"combat_details\"")
    private String combatDetails;

    @Column(name = "\"userid\"")
    private Integer userFightingId;


    public Integer getUserFightingId() {
        return userFightingId;
    }

    public void setUserFightingId(Integer userFightingId) {
        this.userFightingId = userFightingId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }


    public String getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(String selectedItems) {
        this.selectedItems = selectedItems;
    }

    public String getCombatDetails() {
        return combatDetails;
    }

    public void setCombatDetails(String combatDetails) {
        this.combatDetails = combatDetails;
    }

}