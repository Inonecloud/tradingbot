package de.optimaxenergy.strategy;

import de.optimaxenergy.domain.TradeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * This class uses random bid, but it calculates random value from 0 to max allowed like in @Class HighFlowBidStrategy
 */
public class RandomValueStrategy implements BidStrategy {
    private static final Logger log = LoggerFactory.getLogger(RandomValueStrategy.class);
    private Random rand = SecureRandom.getInstanceStrong();

    public RandomValueStrategy() throws NoSuchAlgorithmException {
    }

    @Override
    public int calculateBid(TradeData data) {
        log.debug("Strategy used: {}", this.getClass().getName());

        int remainingRounds = data.getRoundsToWin() - data.getWonRounds();

        if (data.getCurrentMoney() <= 0 || remainingRounds <= 0) {
            return 0;
        }

        int randomLimit = data.getCurrentMoney() / remainingRounds;

        return rand.nextInt(randomLimit);
    }
}
