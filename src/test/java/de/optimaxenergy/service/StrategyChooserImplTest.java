package de.optimaxenergy.service;

import de.optimaxenergy.domain.Bid;
import de.optimaxenergy.strategy.BidStrategy;
import de.optimaxenergy.strategy.HighFlowBidStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;

class StrategyChooserImplTest {

    private final Trader trader = Mockito.mock(Trader.class);

    private final StrategyChooserImpl strategyChooser = new StrategyChooserImpl(trader);

    StrategyChooserImplTest() throws NoSuchAlgorithmException {
    }

    @Test
    void whenNoStrategyChosenThenChooseRandomStrategy() {
        strategyChooser.choose();

        ArgumentCaptor<BidStrategy> captor = ArgumentCaptor.forClass(BidStrategy.class);
        Mockito.verify(trader, Mockito.times(1)).setBidStrategy(captor.capture());

        BidStrategy strategy = captor.getValue();
        assertAll(
                () -> assertNotNull(strategy)
        );
    }

    @Test
    void whenLastRoundWonThenStayWithOldStrategy() {
        Mockito.when(trader.getBidStrategy()).thenReturn(new HighFlowBidStrategy());
        Mockito.when(trader.getOurBids()).thenReturn(Collections.singletonList(Bid.newBuilder()
                .isWon(true)
                .build()));

        strategyChooser.choose();

        Mockito.verify(trader, Mockito.times(0)).setBidStrategy(any(BidStrategy.class));
    }

    @Test
    void whenLastRoundNotWonThenChooseAnotherStrategy() {
        BidStrategy startStrategy = new HighFlowBidStrategy();
        Mockito.when(trader.getBidStrategy()).thenReturn(startStrategy);
        Mockito.when(trader.getOurBids()).thenReturn(Collections.singletonList(Bid.newBuilder()
                .isWon(false)
                .build()));

        strategyChooser.choose();

        ArgumentCaptor<BidStrategy> captor = ArgumentCaptor.forClass(BidStrategy.class);
        Mockito.verify(trader, Mockito.times(1)).setBidStrategy(captor.capture());

        BidStrategy strategy = captor.getValue();
        assertAll(
                () -> assertNotNull(strategy),
                () -> assertNotEquals(startStrategy, strategy)
        );
    }
}