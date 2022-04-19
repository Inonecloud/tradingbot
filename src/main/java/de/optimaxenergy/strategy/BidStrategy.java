package de.optimaxenergy.strategy;

import de.optimaxenergy.domain.TradeData;

/**
 * This interface is a base for Strategy design pattern
 */
public interface BidStrategy {
    /**
     * Calculate a bid.
     *
     * @param data information about start money, how many rounds and how many rounds are necessary to win
     * @return value of bid
     */
    int calculateBid(TradeData data);

}
