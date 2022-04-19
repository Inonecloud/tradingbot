package de.optimaxenergy.strategy;

import de.optimaxenergy.domain.TradeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class uses maximal bid that can help to win
 */
public class HighFlowBidStrategy implements BidStrategy {
    private static final Logger log = LoggerFactory.getLogger(HighFlowBidStrategy.class);


    @Override
    public int calculateBid(TradeData data) {
        log.debug("Strategy useed: {}", this.getClass().getName());

        if (data.getRoundsToWin() > data.getWonRounds()) {
            return data.getCurrentMoney() / (data.getRoundsToWin() - data.getWonRounds());
        }
        return 0;
    }
}
