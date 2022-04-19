package de.optimaxenergy.service;

import de.optimaxenergy.api.Bidder;
import de.optimaxenergy.domain.Bid;
import de.optimaxenergy.strategy.BidStrategy;
import de.optimaxenergy.strategy.FixedFlowBidStrategy;
import de.optimaxenergy.strategy.HighFlowBidStrategy;
import de.optimaxenergy.strategy.RandomValueStrategy;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class StrategyChooserImpl implements StrategyChooser {

    private final Trader trader;

    private final List<BidStrategy> strategies = List.of(
            new FixedFlowBidStrategy(),
            new HighFlowBidStrategy(),
            new RandomValueStrategy()
    );

    private final Random rand = SecureRandom.getInstanceStrong();

    public StrategyChooserImpl(Bidder bidder) throws NoSuchAlgorithmException {
        this.trader = (Trader) bidder;
    }

    public void choose() {
        BidStrategy currentBidStrategy = trader.getBidStrategy();
        if (currentBidStrategy == null) {
            int randomStrategyIndex = rand.nextInt(strategies.size());
            trader.setBidStrategy(strategies.get(randomStrategyIndex));
            return;
        }

        List<Bid> ourBids = trader.getOurBids();

        if (ourBids == null || ourBids.isEmpty()) {
            return;
        }

        Bid lastBid = ourBids.get(ourBids.size() - 1);
        if (!lastBid.isWon()) {
            strategies.stream()
                    .filter(bidStrategy -> bidStrategy != currentBidStrategy)
                    .skip(rand.nextInt(strategies.size() - 1))
                    .findFirst()
                    .ifPresent(trader::setBidStrategy);
        }
    }
}
