package de.optimaxenergy.strategy;

import de.optimaxenergy.domain.TradeData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HighFlowBidStrategyTest {

    private BidStrategy strategy = new HighFlowBidStrategy();

    @Test
    void whenEnoughCurrentMoneyAndNotWonThenReturnedBid() {
        TradeData tradeData = new TradeData(20, 10);

        int result = strategy.calculateBid(tradeData);

        assertAll(
                () -> assertEquals(6, result)
        );
    }

    @Test
    void whenCurrentMoneyIsZeroAndNotWonThenReturnedZero() {
        TradeData tradeData = new TradeData(20, 0, 10);

        int result = strategy.calculateBid(tradeData);

        assertAll(
                () -> assertEquals(0, result)
        );
    }

    @Test
    void whenEnoughProductsToWinAndNotWonThenReturnedZero() throws NoSuchFieldException {
        TradeData tradeData = new TradeData(20, 10, 10, 3);

        int result = strategy.calculateBid(tradeData);

        assertAll(
                () -> assertEquals(0, result)
        );
    }

}