package de.optimaxenergy.strategy;

import de.optimaxenergy.domain.TradeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class uses fixed bid. It returns value equals all money divides to all rounds,
 * but if we have not enough money, then it returns 0 value
 */
public class FixedFlowBidStrategy implements BidStrategy {
    private static final Logger log = LoggerFactory.getLogger(FixedFlowBidStrategy.class);

    @Override
    public int calculateBid(TradeData data) {
        log.debug("Strategy useed: {}", this.getClass().getName());

        int rounds = data.getAllProducts() / 2;
        int bid = data.getStartMoney() / rounds;
        if (bid > data.getCurrentMoney()) {
            return 0;
        }
        if (rounds > 0 && data.getCurrentMoney() > 0 && data.getRoundsToWin() > data.getWonRounds()) {
            return bid;
        }
        return 0;
    }
}
