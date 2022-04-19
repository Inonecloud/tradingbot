package de.optimaxenergy.service;

import de.optimaxenergy.domain.Bid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TraderTest {

    private final Trader bidder = new Trader();

    @BeforeEach
    void init() {
        bidder.init(10, 10);
    }

    @Test
    void whenOwnIsMoreThenOtherThenOwnWon() {
        bidder.bids(10, 1);

        List<Bid> ourBids = bidder.getOurBids();
        Bid result = ourBids.get(ourBids.size() - 1);

        assertAll(
                () -> assertTrue(result.isWon())
        );
    }

    @Test
    void whenOwnIsLessThenOtherThenOwnNotWon() {
        bidder.bids(1, 10);

        List<Bid> ourBids = bidder.getOurBids();
        Bid result = ourBids.get(ourBids.size() - 1);

        assertAll(
                () -> assertFalse(result.isWon())
        );
    }

    @Test
    void whenOwnEqualThenOtherThenOwnNotWon() {
        bidder.bids(10, 10);

        List<Bid> ourBids = bidder.getOurBids();
        Bid result = ourBids.get(ourBids.size() - 1);

        assertAll(
                () -> assertFalse(result.isWon())
        );
    }

}