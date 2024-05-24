package com.pc3r.vfarm.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link com.pc3r.vfarm.entities.Coin}
 */
public class CoinDto implements Serializable {
    private final Integer id;
    @Size(max = 255)
    private final String symbol;
    private final BigDecimal value;
    private final Timestamp lastUpdated;
    private final UserDto owner;

    public CoinDto(Integer id, String symbol, BigDecimal value, Timestamp lastUpdated,
                   UserDto owner) {
        this.id = id;
        this.symbol = symbol;
        this.value = value;
        this.lastUpdated = lastUpdated;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinDto entity = (CoinDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.symbol, entity.symbol) &&
                Objects.equals(this.value, entity.value) &&
                Objects.equals(this.lastUpdated, entity.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, value, lastUpdated);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "symbol = " + symbol + ", " +
                "value = " + value + ", " +
                "lastUpdated = " + lastUpdated + ")";
    }

    public UserDto getOwner() {
        return owner;
    }
}