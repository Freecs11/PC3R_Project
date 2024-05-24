package com.pc3r.vfarm.dao;

import com.pc3r.vfarm.entities.Coin;

import java.math.BigDecimal;
import java.time.Instant;

public class CoinDAO extends HibernateDAO<Coin> {
    public CoinDAO() {
        super(Coin.class);
    }

    //package com.pc3r.vfarm.entities;
    //
    //import jakarta.persistence.*;
    //import jakarta.validation.constraints.NotNull;
    //import jakarta.validation.constraints.Size;
    //
    //import java.math.BigDecimal;
    //import java.time.Instant;
    //import java.util.LinkedHashSet;
    //import java.util.Set;
    //
    //@Entity
    //@Table(name = "\"COINS\"", schema = "public")
    //public class Coin {
    //    @Id
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    @Column(name = "\"ID\"", nullable = false)
    //    private Integer id;
    //
    //    @NotNull
    //    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //    @JoinColumn(name = "\"OWNER_ID\"", nullable = false)
    //    private User owner;
    //
    //    @Size(max = 255)
    //    @NotNull
    //    @Column(name = "\"SYMBOL\"", nullable = false)
    //    private String symbol;
    //
    //    @NotNull
    //    @Column(name = "\"VALUE\"", nullable = false)
    //    private BigDecimal value;
    //
    //    @NotNull
    //    @Column(name = "\"LAST_UPDATED\"", nullable = false)
    //    private Instant lastUpdated;
    //
    //    @OneToMany(mappedBy = "coin")
    //    private Set<Reward> rewards = new LinkedHashSet<>();
    //
    //    public Integer getId() {
    //        return id;
    //    }
    //
    //    public void setId(Integer id) {
    //        this.id = id;
    //    }
    //
    //    public User getOwner() {
    //        return owner;
    //    }
    //
    //    public void setOwner(User owner) {
    //        this.owner = owner;
    //    }
    //
    //    public String getSymbol() {
    //        return symbol;
    //    }
    //
    //    public void setSymbol(String symbol) {
    //        this.symbol = symbol;
    //    }
    //
    //    public BigDecimal getValue() {
    //        return value;
    //    }
    //
    //    public void setValue(BigDecimal value) {
    //        this.value = value;
    //    }
    //
    //    public Instant getLastUpdated() {
    //        return lastUpdated;
    //    }
    //
    //    public void setLastUpdated(Instant lastUpdated) {
    //        this.lastUpdated = lastUpdated;
    //    }
    //
    //    public Set<Reward> getRewards() {
    //        return rewards;
    //    }
    //
    //    public void setRewards(Set<Reward> rewards) {
    //        this.rewards = rewards;
    //    }
    //
    //}

    public Coin findBySymbol(String symbol) {
        return (Coin) getSession().createQuery("from Coin where symbol = :symbol")
                .setParameter("symbol", symbol)
                .uniqueResult();
    }

    public Coin findByOwner(Integer ownerId) {
        return (Coin) getSession().createQuery("from Coin where owner.id = :ownerId")
                .setParameter("ownerId", ownerId)
                .uniqueResult();
    }

    public void updateValue(Integer coinId, BigDecimal value) {
        getSession().createQuery("update Coin set value = :value where id = :coinId")
                .setParameter("value", value)
                .setParameter("coinId", coinId)
                .executeUpdate();
    }

    public Coin createCoin(String symbol, BigDecimal value, Integer ownerId) {
        Coin coin = new Coin();
        coin.setSymbol(symbol);
        coin.setValue(value);

        coin.setOwner(new UserDAO().findById(ownerId));
        coin.setLastUpdated(Instant.now());
        getSession().save(coin);
        return coin;
    }

    public Integer generateCoinId(Integer ownerId) {
        Coin coin = createCoin("TEMP", BigDecimal.ZERO, ownerId);
        return coin.getId();
    }

    public Coin updateCoin(Integer coinId, BigDecimal value) {
        Coin coin = findById(coinId);
        coin.setValue(value);
        coin.setLastUpdated(Instant.now());
        getSession().update(coin);
        return coin;
    }


}

