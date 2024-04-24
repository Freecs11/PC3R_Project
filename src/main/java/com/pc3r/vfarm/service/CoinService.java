package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.CoinDAO;
import com.pc3r.vfarm.entities.Coin;

import java.math.BigDecimal;

public class CoinService extends GenericService<Coin> {
    public CoinService() {
        super(new CoinDAO());
    }

    public Coin getCoinBySymbol(String symbol) {
        return ((CoinDAO) dao).findBySymbol(symbol);
    }

    public Coin createCoin(String symbol, BigDecimal value , Integer ownerId) {
        return ((CoinDAO) dao).createCoin(symbol, value, ownerId);
    }

    public Coin updateCoin(Integer coinId, BigDecimal value) {
        return ((CoinDAO) dao).updateCoin(coinId, value);
    }

    public Integer generateCoinId(Integer ownerId) {
        return ((CoinDAO) dao).generateCoinId(ownerId);
    }


}
