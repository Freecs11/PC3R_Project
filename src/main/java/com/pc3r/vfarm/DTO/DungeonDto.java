package com.pc3r.vfarm.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link com.pc3r.vfarm.entities.Dungeon}
 */
public class DungeonDto implements Serializable {
    private  Integer id;

    @Size(max = 255)
    private  String name;
    @Size(max = 255)
    private  String type;
    private Timestamp createdAt;
    private  BigDecimal localX;
    private  BigDecimal localY;
    private  Timestamp time;
    private String status;
    private String selectedItems;
    private String combatDetails;
    private Integer userFightingId;


    public DungeonDto(Integer id, String name, String type, Timestamp createdAt, BigDecimal localX, BigDecimal localY, Timestamp time , String status , String selectedItems , String combatDetails, Integer userFightingId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
        this.localX = localX;
        this.localY = localY;
        this.time = time;
        this.status = status;
        this.selectedItems = selectedItems;
        this.combatDetails = combatDetails;
        this.userFightingId = userFightingId;
    }

    public DungeonDto(){

    }

    public Integer getUserFightingId() {
        return userFightingId;
    }

    public void setUserFightingId(Integer userFightingId) {
        this.userFightingId = userFightingId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setLocalY(BigDecimal localY) {
        this.localY = localY;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setLocalX(BigDecimal localX) {
        this.localX = localX;
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

    public BigDecimal getLocalX() {
        return localX;
    }

    public BigDecimal getLocalY() {
        return localY;
    }

    public Timestamp getTime() {
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
}