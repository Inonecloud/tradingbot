package de.optimaxenergy.service;

import de.optimaxenergy.api.Bidder;
import de.optimaxenergy.domain.Bid;
import de.optimaxenergy.domain.TradeData;
import de.optimaxenergy.strategy.BidStrategy;

import java.util.ArrayList;
import java.util.List;

public class Trader implements Bidder {
    private TradeData tradeData;
    private BidStrategy bidStrategy;

    private final List<Bid> ourBids = new ArrayList<>();

    @Override
    public void init(int quantity, int cache) {
        tradeData = new TradeData(cache, quantity);
    }

    @Override
    public int placeBid() {
        return bidStrategy.calculateBid(tradeData);
    }

    @Override
    public void bids(int own, int other) {
        if (own > other) {
            ourBids.add(Bid.newBuilder()
                    .isWon(true)
                    .withAmount(own)
                    .withBalance(tradeData.getCurrentMoney() - own)
                    .withQuantityOfProducts(2)
                    .build());
            tradeData.setCurrentMoney(tradeData.getCurrentMoney() - own);
            tradeData.wonRound();
        }
        if (own == other) {
            ourBids.add(Bid.newBuilder()
                    .isWon(false)
                    .withAmount(own)
                    .withBalance(tradeData.getCurrentMoney() - own)
                    .withQuantityOfProducts(1)
                    .build());
            tradeData.setCurrentMoney(tradeData.getCurrentMoney() - own);
        }
        if (own < other) {
            ourBids.add(Bid.newBuilder()
                    .isWon(false)
                    .withAmount(own)
                    .withBalance(tradeData.getCurrentMoney() - own)
                    .withQuantityOfProducts(0)
                    .build());
            tradeData.setCurrentMoney(tradeData.getCurrentMoney() - own);
        }

    }

    public void setBidStrategy(BidStrategy bidStrategy) {
        this.bidStrategy = bidStrategy;
    }

    public BidStrategy getBidStrategy() {
        return bidStrategy;
    }

    public List<Bid> getOurBids() {
        return ourBids;
    }
}
