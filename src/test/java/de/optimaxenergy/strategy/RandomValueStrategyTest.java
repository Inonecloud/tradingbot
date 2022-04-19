package de.optimaxenergy.strategy;

import de.optimaxenergy.domain.TradeData;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class RandomValueStrategyTest {
    private BidStrategy strategy = new RandomValueStrategy();

    RandomValueStrategyTest() throws NoSuchAlgorithmException {
    }

    @Test
    void wheEnoughMoneyAndEnoughRoundsThenReturnedBid() {
        TradeData tradeData = new TradeData(20, 10);

        int result = strategy.calculateBid(tradeData);

        assertAll(
                () -> assertTrue(result > 0 && result < 6)
        );
    }

    @Test
    void whenCurrenMoneyLessOrEqualsZeroThenReturnedZero() {
        TradeData tradeData = new TradeData(20, 0, 10);

        int result = strategy.calculateBid(tradeData);

        assertAll(
                () -> assertEquals(0, result)
        );
    }

    @Test
    void whenRemainingRoundsLessOrEqualsZeroThemReturnedZero() {
        TradeData tradeData = new TradeData(20, 10, 10, 5);

        int result = strategy.calculateBid(tradeData);

        assertAll(
                () -> assertEquals(0, result)
        );
    }


}