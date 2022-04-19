package de.optimaxenergy.strategy;

import de.optimaxenergy.domain.TradeData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FixedFlowBidStrategyTest {

    private BidStrategy strategy = new FixedFlowBidStrategy();


    @Test
    void whenEnoughMoneyAndNoWinsThenReturnedBid() {
        TradeData tradeData = new TradeData(20, 20, 10);
        int result = strategy.calculateBid(tradeData);

        assertAll(
                () -> assertEquals(4, result)
        );
    }

    @Test
    void whenBidEqualsCurrentMoneyThenReturnedZero() {
        TradeData tradeData = new TradeData(20, 4, 10);

        int result = strategy.calculateBid(tradeData);

        assertAll(
                () -> assertEquals(4, result)
        );
    }

    @Test
    void whenBidMoreThenCurrentMoneyThenReturnedZero() {
        TradeData tradeData = new TradeData(20, 3, 10);

        int result = strategy.calculateBid(tradeData);

        assertAll(
                () -> assertEquals(0, result)
        );
    }

    @Test
    void whenStartMoneyIsZeroThenReturnedZero() {
        TradeData tradeData = new TradeData(0, 10);

        int result = strategy.calculateBid(tradeData);

        assertAll(
                () -> assertEquals(0, result)
        );
    }


}